package com.example.demo.entity.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

public class Nls implements UserType, Serializable {
    private static final long serialVersionUID = -4363175251635937661L;
    private final String checkComma = ",";
    private final String replaceCheckComma = "#@";


    private final int[] sqlTypesSupported = new int[]{Types.VARCHAR};
    //private final int[] sqlTypesSupported = new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

    private String ru;
    private String uz_cl;
    private String uz_lat;
    private String en;

    @JsonIgnore
    private String qr;

    public Nls() {
        this.ru = null;
        this.uz_cl = null;
        this.uz_lat = null;
        this.en = null;
        this.qr = null;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        if (ru == null) return;

        if (ru.contains(checkComma)) {
            ru = ru.replaceAll(checkComma, replaceCheckComma);
        }

        this.ru = ru;
    }

    public String getUz_cl() {
        return uz_cl;
    }

    public void setUz_cl(String uz_cl) {
        if (uz_cl == null) return;

        if (uz_cl.contains(checkComma)) {
            uz_cl = uz_cl.replaceAll(checkComma, replaceCheckComma);
        }


        this.uz_cl = uz_cl;
    }

    public String getUz_lat() {
        return uz_lat;
    }

    public void setUz_lat(String uz_lat) {
        if (uz_lat != null && uz_lat.contains(checkComma)) {
            uz_lat = uz_lat.replaceAll(checkComma, replaceCheckComma);
        }
        this.uz_lat = uz_lat;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        if (en != null && en.contains(checkComma)) {
            en = en.replaceAll(checkComma, replaceCheckComma);
        }

        this.en = en;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        if (qr != null && qr.contains(checkComma)) {
            qr = qr.replaceAll(checkComma, replaceCheckComma);
        }
        this.qr = qr;
    }


    public Nls(String ru, String uz_cl, String uz_lat, String en, String qr) {
        super();
        this.ru = ru;
        this.uz_cl = uz_cl;
        this.uz_lat = uz_lat;
        this.qr = qr;
        this.en = en;

    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        if (!(cached instanceof Nls)) {
            throw new HibernateException("invalid argument");
        }
        Nls f = (Nls) cached;
        return new Nls(f.getRu(), f.getUz_cl(), f.getUz_lat(), f.getEn(), f.getQr());
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        if (!(value instanceof Nls)) {
            throw new HibernateException("invalid argument");
        }
        return (Nls) value;
    }

    @Override
    public int[] sqlTypes() {
        return sqlTypesSupported;
    }

    @Override
    public Class<Nls> returnedClass() {
        return Nls.class;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        //logger.debug("names[0] = " + names[0] + " value = " + rs.getString(names[0]) + " length = " + names.length);

        if (names == null || names.length == 0) return null;

    /*    if (rs.wasNull()) {
            return null;
        }
*/
        String nlsName;
        try {
            nlsName = rs.getString(names[0]).replace("(", "").replace(")", "");
        } catch (Exception ex) {
            return null;
        }

        nlsName = nlsName.replaceAll("\"", "");
        String[] values = nlsName.split(",");


//		String[] values =(String[]) Arrays.stream(rsArray).toArray();;//--.replace("(", "").replace(")", "").split(",");
        //	logger.debug("values length = " + values.length);

        this.ru = null;
        this.uz_cl = null;
        this.uz_lat = null;
        this.en = null;
        this.qr = null;

        for (int i = 0; i < values.length; i++) {
            switch (i) {
                case 0:
                    //			logger.debug("values[" + i + "] = " + values[i]);
                    ru = values[i];

                    break;

                case 1:
                    //			logger.debug("values[" + i + "] = " + values[i]);
                    uz_cl = values[i];
                    break;

                case 2:
                    //			logger.debug("values[" + i + "] = " + values[i]);
                    uz_lat = values[i];
                    break;

                case 3:
                    //			logger.debug("values[" + i + "] = " + values[i]);
                    en = values[i];
                    break;

                case 4:
                    //			logger.debug("values[" + i + "] = " + values[i]);
                    qr = values[i];
                    break;

                default:
                    //			logger.error("Index " + i + " unknown!");
                    return null;

            }
        }

        if (uz_lat != null && uz_lat.contains(replaceCheckComma)) {
            uz_lat = uz_lat.replaceAll(replaceCheckComma, checkComma);
        }

        if (ru != null && ru.contains(replaceCheckComma)) {
            ru = ru.replaceAll(replaceCheckComma, checkComma);
        }

        if (uz_cl != null) {

            if (uz_cl.contains(replaceCheckComma)) {
                uz_cl = uz_cl.replaceAll(replaceCheckComma, checkComma);
            }

            String constCrlLowerG = "&#1171;";
            if (uz_cl.contains(constCrlLowerG)) {
                uz_cl = uz_cl.replaceAll(constCrlLowerG, "ғ");
            }
            String constCrlUpperG = "&#1170;";
            if (uz_cl.contains(constCrlUpperG)) {
                uz_cl = uz_cl.replaceAll(constCrlUpperG, "Ғ");
            }


            String constCrlLowerH = "&#1203;";
            if (uz_cl.contains(constCrlLowerH)) {
                uz_cl = uz_cl.replaceAll(constCrlLowerH, "ҳ");
            }

            String constCrlUpperH = "&#1202;";
            if (uz_cl.contains(constCrlUpperH)) {
                uz_cl = uz_cl.replaceAll(constCrlUpperH, "Ҳ");
            }


            String constCrlLowerQ = "&#1179;";
            if (uz_cl.contains(constCrlLowerQ)) {
                uz_cl = uz_cl.replaceAll(constCrlLowerQ, "қ");
            }

            String constCrlUpperQ = "&#1178;";
            if (uz_cl.contains(constCrlUpperQ)) {
                uz_cl = uz_cl.replaceAll(constCrlUpperQ, "Қ");
            }
        }

        Nls customField = new Nls(ru, uz_cl, uz_lat, en, qr);
        return customField;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        String postgresSpecific;

        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else if (!(value instanceof Nls)) {
            throw new HibernateException("invalid argument");
        } else {

            final Nls f = (Nls) value;
            postgresSpecific = "(" + (f.getRu() == null ? "" : f.getRu()) + "," + (f.getUz_cl() == null ? "" : f.getUz_cl()) + "," + (f.getUz_lat() == null ? "" : f.getUz_lat()) + "," + (f.getEn() == null ? "" : f.getEn()) + "," + (f.getQr() == null ? "" : f.getQr()) + ")";
            st.setObject(index, postgresSpecific, Types.OTHER);
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;

        if (!(value instanceof Nls)) {
            throw new HibernateException("invalid argument");
        }

        Nls f = (Nls) value;
        return new Nls(ru, uz_cl, uz_lat, en, qr);

    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
    }

    @Hidden
    @Override
    public boolean isMutable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Hidden
    @JsonIgnore
    public String getActiveLanguage() {

        Locale locale = LocaleContextHolder.getLocale();
        String name = ru;

        switch (locale.toString().toLowerCase()) {
            case "uz_kir":
                name = uz_cl;
                break;
            case "uz_lat":
                name = uz_lat;
                break;
            default:
                break;
        }

        if (name == null) {
            name = ru;
            if (name == null) {
                name = uz_lat;
                if (name == null) name = uz_cl;
            }
        }

        if (name != null && name.contains(replaceCheckComma)) {
            name = name.replaceAll(replaceCheckComma, checkComma);
        }

        return name;
    }
}