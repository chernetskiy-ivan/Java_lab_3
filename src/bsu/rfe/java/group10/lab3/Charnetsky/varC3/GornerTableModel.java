package bsu.rfe.java.group10.lab3.Charnetsky.varC3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients){
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom(){
        return from;
    }

    public Double getTo(){
        return to;
    }

    public Double getStep(){
        return step;
    }

    public int getColumnCount(){
        //в данной модели 4 столбца
        return 4;
    }

    public int getRowCount(){
        // Вычислить количество точек между началом и концом отрезка
        // исходя из шага табулирования
        return  new Double(Math.ceil((to - from)/step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col){
        // вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        if(col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        }
        else if(col == 1){
            // Если запрашивается значение 2-го столбца, то это значение многочлена
            // Вычисление значения в точке по схеме Горнера.
            Double result = coefficients[coefficients.length - 1];
            Double slag;
            Double member = 0.0;
            for (int i = 0; i < coefficients.length - 1; i++)
            {
                slag = member + coefficients[i];
                member = slag * x;
            }
            result += member;
            return result;
        }
        else if(col == 2){
            Float result = (float) (double)coefficients[coefficients.length-1];
            Float slag;
            Float member = 0f;
            for(int i = 0 ; i < coefficients.length - 1; i++){
                slag = member + (float) (double)coefficients[i];
                member = slag * (float)x;
            }
            result += member;
            return result;
        }
        else if(col == 3)
        {
            Double difference = (double)getValueAt(row,1) - (double) (float)getValueAt(row,2);
            return difference;
        }
        return null;
    }

    public String getColumnName(int col){
        switch(col){
            case 0:
                //Значение первого столбца
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Значение многочлена(Float)";
            default:
                return "Разница значений";
        }
    }

    public Class<?> getColumnClass(int col){
        switch(col) {
            case 2:
                return Float.class;
            default:
                return Double.class;
        }
    }

}