import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// ALARM: BAD CODE BAD CODE BAD CODE BAD CODE
// ALARM: BAD CODE BAD CODE BAD CODE BAD CODE
// ALARM: BAD CODE BAD CODE BAD CODE BAD CODE

public class MainGUI extends JFrame {
    public MainGUI() {
        // Set dark theme colors
        Color backgroundColor = new Color(45, 45, 45); // Dark gray
        Color foregroundColor = Color.WHITE;
        Color headerColor = new Color(70, 70, 70); // Slightly lighter gray
        Color cellHighlightColor = new Color(100, 100, 100); // Lighter gray for highlighted cells

        setTitle("Код Хэмминга Калькулятор");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создание модели таблицы
        String[] columnNames = {"r1", "r2", "i1", "r3", "i2", "i3", "i4"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
        JTable table = new JTable(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        // Переопределение метода prepareRenderer для выделения активной ячейки
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                component.setBackground(Color.WHITE);
                return component;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.setFont(new Font("Calibria", Font.PLAIN, 25));
        table.setRowHeight(40);
        table.setAlignmentX(20);
        // Настройка внешнего вида заголовков столбцов
        table.getTableHeader().setFont(new Font("Calibria", Font.BOLD, 25));

        // Настройка внешнего вида ячеек для колонок r1, r2, r3
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(cellHighlightColor);
        cellRenderer.setForeground(foregroundColor);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        add(scrollPane, BorderLayout.CENTER);

        JLabel resultLabel = new JLabel("~ ожидание ввода ~", SwingConstants.CENTER);
        JTextArea trace = new JTextArea();
        trace.setEditable(false);
        trace.setFont(new Font("Calibria", Font.PLAIN, 20));

        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column != TableModelEvent.ALL_COLUMNS) {
                String value = (String) model.getValueAt(row, column);
                if (value != null && value.length() > 1) {
                    // Оставить только последний символ
                    value = value.substring(value.length() - 1);
                    model.setValueAt(value, row, column);
                }

            }

            // check for result
            StringBuilder buff = new StringBuilder();
            for (int r = 0; r < 7; r++) {
                if (model.getValueAt(0, r) == null)
                    return;
                buff.append(model.getValueAt(0, r));
            }

            StringBuilder traceBuilder = new StringBuilder();
            String enteredValue = buff.toString();

            final int[] bits = Arrays.stream(enteredValue.split("")).limit(7).mapToInt(Integer::parseInt).toArray();
            final int[] syndrome = calculatingSyndrome(bits);

            traceBuilder.append(String.format("s1 = r1 ⊕ i1 ⊕ i2 ⊕ i4 = %d ⊕ %d ⊕ %d ⊕ %d = %d", bits[0], bits[2], bits[4], bits[6], syndrome[0])).append("\n");
            traceBuilder.append(String.format("s2 = r2 ⊕ i1 ⊕ i3 ⊕ i4 = %d ⊕ %d ⊕ %d ⊕ %d = %d", bits[1], bits[2], bits[5], bits[6], syndrome[1])).append("\n");
            traceBuilder.append(String.format("s3 = r3 ⊕ i2 ⊕ i3 ⊕ i4 = %d ⊕ %d ⊕ %d ⊕ %d = %d", bits[3], bits[4], bits[5], bits[6], syndrome[2])).append("\n");


            if (hasError(syndrome)) {
                String wrongElement = calculatingWrongElement(calculatingIndex(syndrome));
                resultLabel.setText(String.format("Правильное сообщение: %s",
                       returnCorrectMessage(bits, wrongElement)));

                final int errindex = Integer.parseInt(Arrays.stream(syndrome).boxed().map(String::valueOf).collect(Collectors.joining()), 2);
                traceBuilder.append(String.format("%n S = (s3,s2,s1) = %d%d%d = %d => \n => ошибка на %d позиции, т.е. ошибка в %s",
                        syndrome[2], syndrome[1], syndrome[0], errindex, errindex, wrongElement));

                trace.setText(traceBuilder.toString());
                return;
            }

            traceBuilder.append("\nS = (s3,s2,s1) = 000 = 0 => ошибки нет");

            resultLabel.setText(String.format("Сообщение без ошибок: %s", makeMessageFromList(bits)));
            trace.setText(traceBuilder.toString());
        });

        // Настройка редактора ячеек для контроля вводимых значений
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean stopCellEditing() {
                    String value = (String) getCellEditorValue();
                    if (value.matches("[01]+")) {
                        return super.stopCellEditing();
                    } else {
                        return false;
                    }
                }
            });
        }


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        resultLabel.setFont(new Font("Calibria", Font.BOLD, 25));

        bottomPanel.add(trace, BorderLayout.CENTER);
        bottomPanel.add(resultLabel, BorderLayout.PAGE_END);

        add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(backgroundColor);
        table.setBackground(backgroundColor);
        table.setForeground(foregroundColor);
        table.getTableHeader().setBackground(headerColor);
        table.getTableHeader().setForeground(foregroundColor);
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setForeground(foregroundColor);
        trace.setBackground(backgroundColor);
        trace.setForeground(foregroundColor);
        resultLabel.setForeground(foregroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        });
    }


    public int[] calculatingSyndrome(int[] bits) {
        int s1 = (bits[0] + bits[2] + bits[4] + bits[6]) % 2;
        int s2 = (bits[1] + bits[2] + bits[5] + bits[6]) % 2;
        int s3 = (bits[3] + bits[4] + bits[5] + bits[6]) % 2;

        info("s1 = %d, s2 = %d, s3 = %d", s1, s2, s3);

        return new int[]{s1, s2, s3};
    }

    public boolean hasError(int[] bitsTuple) {
        info("checking error %s", Arrays.toString(bitsTuple));
        return bitsTuple[0] != 0 || bitsTuple[1] != 0 || bitsTuple[2] != 0;
    }

    public int calculatingIndex(int[] syndrome) {
        info("calculating index with syndrome ", Arrays.toString(syndrome));
        StringBuilder reversedSyndrome = new StringBuilder();
        for (int i = syndrome.length - 1; i >= 0; i--) {
            reversedSyndrome.append(syndrome[i]);
            info("-- appending %d reversed syndrome", syndrome[i]);
        }

        final int index = Integer.parseInt(reversedSyndrome.toString(), 2);
        info("reversed syndrome equals to %s = %d(10) => error on %d index", reversedSyndrome.toString(), index, index);
        return index;
    }

    private static final String[] symbols = {"r1", "r2", "i1", "r3", "i2", "i3", "i4"};

    public String calculatingWrongElement(int index) {
        info("calculating wrong element, index = %d", index);
        return symbols[index - 1];
    }

    public String makeMessageFromList(final int[] bits) {
        info("converting %s to message (pick indexes 2,4,5,6)", Arrays.toString(bits));
        return IntStream.of(2, 4, 5, 6)
                .map(i -> bits[i]).boxed()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public int getInverse(int element) {
        return (element == 0) ? 1 : 0;
    }

    public int[] getCorrectList(int[] bits, String wrongElement) {
        int wrongElementIndex = Character.getNumericValue(wrongElement.charAt(wrongElement.length() - 1)) - 1 + 3;
        bits[wrongElementIndex] = getInverse(bits[wrongElementIndex]);
        info("wrong element at index %d, inverse it and get %s", wrongElementIndex, Arrays.toString(bits));
        return bits;
    }

    public String returnCorrectMessage(int[] bits, String wrongElement) {
        if (wrongElement.charAt(0) == 'r') {
            info("error in element %s, skip and return value", wrongElement);
            return makeMessageFromList(bits);
        }
        return makeMessageFromList(getCorrectList(bits, wrongElement));
    }

    private boolean debug;
    private static long counter = 0;

    private void info(String message, Object... data) {
        info(String.format(message, data));
    }

    private void info(String message) {
        if (!debug) return;
        System.out.printf("%d [INFO] %s%n", counter++, message);
    }
}
