package com.dxg.msns.dynamic.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "dynamic_type")
public class DynamicType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeName;
    private String typeStatus;
    @Transient
    private Boolean typeStatusBoolean;

    public Boolean getTypeStatusBoolean() {
        if (typeStatusBoolean == null){
            if (this.typeStatus != null){
                if (this.typeStatus.equals("0")){
                    this.typeStatusBoolean = false;
                }else if (this.typeStatus.equals("1")){
                    this.typeStatusBoolean = true;
                }else {
                    this.typeStatusBoolean = false;
                }
            }
        }
        return typeStatusBoolean;
    }

    public void setTypeStatusBoolean(Boolean typeStatusBoolean) {

        this.typeStatusBoolean = typeStatusBoolean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    @Override
    public String toString() {
        return "DynamicType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", typeStatus='" + typeStatus + '\'' +
                ", typeStatusBoolean=" + typeStatusBoolean +
                '}';
    }
}
