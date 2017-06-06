package br.edu.utfpr.gabriel.financeiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gabriel on 27/11/16.
 */

public class FormatAll {
    public static final String FORMATO_DIA = "dd";
    public static final String FORMATO_MES = "MM";
    public static final String[] MES = {
            "JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"
    };

    public static String formatData(Date data, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(data);


    }

    public  static String formatValor(float valor){
        return String.format("R$ %.2f",valor);


    }

    public static Date formatStringForDate(String data,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            return null;
        }

    }

}
