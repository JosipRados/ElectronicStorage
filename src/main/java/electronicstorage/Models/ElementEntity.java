package electronicstorage.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ElementEntity {
    public long id;
    public String code;
    public String value;
    public String unit;
    public String type;
    public String size;
    public String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ElementEntity{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}