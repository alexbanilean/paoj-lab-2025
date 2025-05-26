package practice;

/*
 * Aplicație Swing pentru Gestionarea unei Liste de Teme și Priorități
 *
 * Descriere generală:
 * Creați o aplicație desktop GUI care permite utilizatorului să adauge,
 * să vizualizeze, să modifice și să elimine teme (task-uri) cu diverse niveluri
 * de prioritate și stări de finalizare.
 *
 * Cerințe funcționale:
 *
 * Fereastra principală (JFrame):
 * - Titlu: „Task Manager Simplu”
 * - Dimensiune fixă recomandată (ex: 700x500 px)
 * - Inițializare în EDT cu SwingUtilities.invokeLater.
 *
 * Componente principale:
 * - JTable care afișează lista temelor, cu coloanele:
 *     • Descriere (String)
 *     • Prioritate (combo box în celula tabelului, valori: Scăzută, Medie, Ridicată)
 *     • Status (checkbox pentru marcat finalizat / nefinalizat)
 *     • Dată limită (JTextField cu validare format dată simplu, ex: YYYY-MM-DD)
 *
 * - Buton Adaugă Task care deschide un dialog modal cu un formular pentru introducerea unui task nou
 *   (descriere, prioritate, dată limită).
 * - Buton Șterge Task care șterge task-ul selectat în tabel.
 * - Buton Modifică Task care deschide un dialog modal pentru editarea task-ului selectat.
 *
 * Dialog de adăugare/modificare task:
 * - Câmpuri pentru descriere, prioritate (combo box), dată limită (text), stare (checkbox finalizat).
 * - Buton Salvare, Buton Anulare.
 * - Validare input (ex: descriere obligatorie, dată validă).
 *
 * Funcționalități avansate:
 * - Sortare după prioritate (implicită, temele cu prioritate ridicată să apară primele).
 * - Colorare rânduri în tabel:
 *     • Verde pentru task-uri finalizate.
 *     • Roșu pentru task-uri cu data limită depășită și nefinalizate.
 * - Filtrare simplă (ex: checkbox în interfață „Afișează doar task-urile nefinalizate”).
 *
 * Gestionarea evenimentelor:
 * - Evenimente pentru butoane, validare, modificare în tabel (editarea directă a celulelor).
 * - Folosirea corectă a EDT pentru toate modificările grafice.
 * - Actualizarea UI după fiecare modificare (refresh tabel, sortare, colorare).
 *
 * Extra:
 * - Salvarea și încărcarea listei task-urilor într-un fișier text (serializare simplă).
 * - Confirmări înainte de ștergerea unui task.
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class Ex1_TaskManagerGUI {

    // Enum pentru Prioritate
    enum Priority {
        SCĂZUTĂ, MEDIE, RIDICATĂ
    }

    // Clasa Task: modelul datelor pentru un task
    static class Task {
        private String description;
        private Priority priority;
        private LocalDate dueDate;
        private boolean completed;

        public Task(String description, Priority priority, LocalDate dueDate, boolean completed) {
            this.description = description;
            this.priority = priority;
            this.dueDate = dueDate;
            this.completed = completed;
        }

        // Getteri și setteri
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public Priority getPriority() { return priority; }
        public void setPriority(Priority priority) { this.priority = priority; }

        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

        public boolean isCompleted() { return completed; }
        public void setCompleted(boolean completed) { this.completed = completed; }
    }

    // Model personalizat pentru JTable, extinde AbstractTableModel
    static class TaskTableModel extends AbstractTableModel {

        private final String[] columns = {"Descriere", "Prioritate", "Dată limită", "Finalizat"};
        private final Class<?>[] columnClasses = {String.class, Priority.class, LocalDate.class, Boolean.class};

        private List<Task> tasks;

        public TaskTableModel(List<Task> tasks) {
            this.tasks = tasks;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public Task getTaskAt(int row) {
            if (row < 0 || row >= tasks.size()) return null;
            return tasks.get(row);
        }

        public void addTask(Task t) {
            tasks.add(t);
            sortByPriority();
            fireTableDataChanged();
        }

        public void removeTask(int index) {
            if (index < 0 || index >= tasks.size()) return;
            tasks.remove(index);
            fireTableDataChanged();
        }

        public void updateTask(int index, Task newTask) {
            if (index < 0 || index >= tasks.size()) return;
            tasks.set(index, newTask);
            sortByPriority();
            fireTableDataChanged();
        }

        // Sortează task-urile descrescător după prioritate (RIDICATĂ > MEDIE > SCĂZUTĂ)
        public void sortByPriority() {
            tasks.sort((t1, t2) -> t2.getPriority().ordinal() - t1.getPriority().ordinal());
        }

        @Override
        public int getRowCount() {
            return tasks.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClasses[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Permitem editarea doar în coloana Finalizat (checkbox)
            return columnIndex == 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Task t = tasks.get(rowIndex);
            switch (columnIndex) {
                case 0: return t.getDescription();
                case 1: return t.getPriority();
                case 2: return t.getDueDate();
                case 3: return t.isCompleted();
                default: return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Task t = tasks.get(rowIndex);
            if (columnIndex == 3 && aValue instanceof Boolean) {
                t.setCompleted((Boolean) aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }
    }

    // Renderer personalizat pentru colorarea rândurilor în funcție de status
    static class TaskTableCellRenderer extends DefaultTableCellRenderer {

        private TaskTableModel model;

        public TaskTableCellRenderer(TaskTableModel model) {
            this.model = model;
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Task task = model.getTaskAt(row);
            if (task == null) return c;

            if (isSelected) {
                c.setBackground(table.getSelectionBackground());
                c.setForeground(table.getSelectionForeground());
            } else {
                // Verde pentru task finalizat
                if (task.isCompleted()) {
                    c.setBackground(new Color(198, 239, 206));
                    c.setForeground(Color.BLACK);
                }
                // Roșu pentru task nefinalizat cu dată limită depășită
                else if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now())) {
                    c.setBackground(new Color(255, 199, 206));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }
            return c;
        }
    }

    // Dialog modal pentru adăugare/modificare task
    static class TaskDialog extends JDialog {
        private JTextField descriptionField;
        private JComboBox<Priority> priorityCombo;
        private JTextField dueDateField;
        private JCheckBox completedCheck;

        private boolean saved = false;

        public TaskDialog(Frame owner, String title, Task task) {
            super(owner, title, true);
            initComponents(task);
        }

        private void initComponents(Task task) {
            setLayout(new BorderLayout());

            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.anchor = GridBagConstraints.WEST;

            // Descriere
            gbc.gridx = 0; gbc.gridy = 0;
            formPanel.add(new JLabel("Descriere:"), gbc);
            gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            descriptionField = new JTextField(20);
            formPanel.add(descriptionField, gbc);

            // Prioritate
            gbc.gridx = 0; gbc.gridy++;
            gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
            formPanel.add(new JLabel("Prioritate:"), gbc);
            gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            priorityCombo = new JComboBox<>(Priority.values());
            formPanel.add(priorityCombo, gbc);

            // Dată limită
            gbc.gridx = 0; gbc.gridy++;
            gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
            formPanel.add(new JLabel("Dată limită (YYYY-MM-DD):"), gbc);
            gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
            dueDateField = new JTextField(20);
            formPanel.add(dueDateField, gbc);

            // Finalizat
            gbc.gridx = 0; gbc.gridy++;
            gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
            formPanel.add(new JLabel("Finalizat:"), gbc);
            gbc.gridx = 1;
            completedCheck = new JCheckBox();
            formPanel.add(completedCheck, gbc);

            add(formPanel, BorderLayout.CENTER);

            // Butoane Salvare și Anulare
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton saveBtn = new JButton("Salvează");
            JButton cancelBtn = new JButton("Anulează");

            buttonsPanel.add(saveBtn);
            buttonsPanel.add(cancelBtn);
            add(buttonsPanel, BorderLayout.SOUTH);

            saveBtn.addActionListener(e -> onSave());
            cancelBtn.addActionListener(e -> onCancel());

            // Populează câmpuri dacă task != null (editare)
            if (task != null) {
                descriptionField.setText(task.getDescription());
                priorityCombo.setSelectedItem(task.getPriority());
                dueDateField.setText(task.getDueDate() != null ? task.getDueDate().toString() : "");
                completedCheck.setSelected(task.isCompleted());
            }

            pack();
            setLocationRelativeTo(getOwner());
        }

        private void onSave() {
            String desc = descriptionField.getText().trim();
            String dueDateStr = dueDateField.getText().trim();

            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrierea este obligatorie.", "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate dueDate = null;
            if (!dueDateStr.isEmpty()) {
                try {
                    dueDate = LocalDate.parse(dueDateStr);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Data trebuie să fie în formatul YYYY-MM-DD.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            saved = true;
            setVisible(false);
        }

        private void onCancel() {
            saved = false;
            setVisible(false);
        }

        public boolean isSaved() {
            return saved;
        }

        public Task getTask() {
            if (!saved) return null;
            String desc = descriptionField.getText().trim();
            Priority priority = (Priority) priorityCombo.getSelectedItem();
            String dueDateStr = dueDateField.getText().trim();
            LocalDate dueDate = null;
            if (!dueDateStr.isEmpty()) {
                dueDate = LocalDate.parse(dueDateStr);
            }
            boolean completed = completedCheck.isSelected();
            return new Task(desc, priority, dueDate, completed);
        }
    }

    // Clasa principală GUI
    static class TaskManagerFrame extends JFrame {

        private TaskTableModel model;
        private JTable table;
        private JCheckBox filterCheckbox;

        public TaskManagerFrame() {
            super("Task Manager Simplu");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(700, 500);
            setLocationRelativeTo(null);
            initComponents();
        }

        private void initComponents() {
            model = new TaskTableModel(new ArrayList<>());

            table = new JTable(model);

            // Setăm renderer personalizat
            TaskTableCellRenderer renderer = new TaskTableCellRenderer(model);
            for (int i = 0; i < model.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }

            // Celula Prioritate sa fie combo box când editezi
            TableColumn priorityColumn = table.getColumnModel().getColumn(1);
            JComboBox<Priority> priorityCombo = new JComboBox<>(Priority.values());
            priorityColumn.setCellEditor(new DefaultCellEditor(priorityCombo));

            // Celula Finalizat este checkbox - deja este Boolean, JTable folosește checkbox automat

            JScrollPane scrollPane = new JScrollPane(table);

            // Butoane
            JButton addBtn = new JButton("Adaugă Task");
            JButton editBtn = new JButton("Modifică Task");
            JButton deleteBtn = new JButton("Șterge Task");
            filterCheckbox = new JCheckBox("Afișează doar task-uri nefianlizate");

            // Panel butoane
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.add(addBtn);
            buttonsPanel.add(editBtn);
            buttonsPanel.add(deleteBtn);
            buttonsPanel.add(filterCheckbox);

            add(scrollPane, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.SOUTH);

            // Adaugă task nou
            addBtn.addActionListener(e -> onAddTask());

            // Șterge task selectat
            deleteBtn.addActionListener(e -> onDeleteTask());

            // Modifică task selectat
            editBtn.addActionListener(e -> onEditTask());

            // Filtrare checkbox
            filterCheckbox.addActionListener(e -> onFilterChanged());

            // Listen pentru modificare directă pe checkbox "Finalizat" în tabel
            model.addTableModelListener(e -> {
                if (e.getColumn() == 3) { // finalizat schimbat
                    model.sortByPriority();
                    model.fireTableDataChanged();
                }
            });
        }

        private void onAddTask() {
            TaskDialog dialog = new TaskDialog(this, "Adaugă Task", null);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                model.addTask(dialog.getTask());
                applyFilter();
            }
        }

        private void onDeleteTask() {
            int selected = table.getSelectedRow();
            if (selected == -1) {
                JOptionPane.showMessageDialog(this, "Selectați un task pentru a șterge.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sigur doriți să ștergeți task-ul selectat?",
                    "Confirmare ștergere",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeTask(selected);
                applyFilter();
            }
        }

        private void onEditTask() {
            int selected = table.getSelectedRow();
            if (selected == -1) {
                JOptionPane.showMessageDialog(this, "Selectați un task pentru a modifica.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Task task = model.getTaskAt(selected);
            TaskDialog dialog = new TaskDialog(this, "Modifică Task", task);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                model.updateTask(selected, dialog.getTask());
                applyFilter();
            }
        }

        private void onFilterChanged() {
            applyFilter();
        }

        // Filtrare task-uri după checkbox
        private void applyFilter() {
            boolean showOnlyIncomplete = filterCheckbox.isSelected();
            if (!showOnlyIncomplete) {
                // Afișăm toate task-urile (modelul deja conține toate)
                model.fireTableDataChanged();
                return;
            }

            // Pentru filtrare, creăm o listă temporară cu doar task-urile nefinalizate
            List<Task> filtered = new ArrayList<>();
            for (Task t : model.getTasks()) {
                if (!t.isCompleted()) {
                    filtered.add(t);
                }
            }

            // Modificăm modelul pentru a afișa doar task-urile filtrate
            // Soluția simplă: înlocuim lista modelului
            model.tasks = filtered;
            model.fireTableDataChanged();
        }
    }

    // Metoda principală
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManagerFrame frame = new TaskManagerFrame();
            frame.setVisible(true);
        });
    }
}

