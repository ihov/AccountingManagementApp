package cl.ihov.project.view.utils;

import java.util.regex.Pattern;

public class ValidatorUtils {

    public static boolean rut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    public static boolean email(String email) {
        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }

    public static boolean telefono(String telefono) {
        return Pattern.compile("^\\(\\d{2,3}\\)\\d{7,8}$").matcher(telefono).matches();
    }

    public static boolean celular(String celular) {
        return Pattern.compile("^\\(\\d{1}\\)\\d{8}$").matcher(celular).matches();
    }

    public static boolean monto(String celular) {
        return Pattern.compile("\\d+").matcher(celular).matches();
    }

    public static boolean nroCuenta(String celular) {
        return Pattern.compile("\\d+").matcher(celular).matches();
    }
}
