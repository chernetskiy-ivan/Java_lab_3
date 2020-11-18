package bsu.rfe.java.group10.lab3.Charnetsky.varC3;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class GornerTableCellRenderer implements TableCellRenderer {

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();

    // Ищем ячейки, строковое представление которых совпадает с needle
    // (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
    // стога сена - таблица

    private String needle = null;

    private DecimalFormat formatter= (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer(){
        //Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку (т.е. не отделять тысячи
        // ни запятыми, ни пробелами), т.е. показывать число как "1000",
        // ане"1 000" или"1,000"
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку, а не
        // запятую. По умолчанию, в региональных настройках
        // Россия/Беларусь дробнаячасть отделяется запятой
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        //Разместить надпись внутри панели
        panel.add(label);
        //Установить выравнивание надписи по левому краю
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
        // Преобразовать double в строку с помощью форматировщика
        String formatterDouble = formatter.format(value);
        // Установить текст надписи равным строковому представлению числа
        label.setText(formatterDouble);
        if(col==1 && needle!=null && needle.equals(formatterDouble)){
            // Номер столбца = 1 (т.е. второй столбец) + иголка не null
            // (значит что-то ищем) +
            // значение иголки совпадает со значением ячейки таблицы -
            // окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
        } else{
            //Иначе в обычный белый
            panel.setBackground(Color.WHITE);
        }
        return panel;
    }

    public void setNeedle(String needle){
        this.needle = needle;
    }

}