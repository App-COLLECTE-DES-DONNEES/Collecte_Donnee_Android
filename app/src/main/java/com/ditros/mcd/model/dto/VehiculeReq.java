package com.ditros.mcd.model.dto;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class VehiculeReq implements Parcelable {
    private Long vehicleAccidentNumber;
    private String plate;
    private String chassis;
    private Long vehicleId;
    private Long type;
    private Long brand;
    private Long model;
    private int fabricationYear;
    private int cylinderCapacity;
    private Long specialFunction;
    private Long vehicleAction;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.vehicleAccidentNumber);
        dest.writeString(this.plate);
        dest.writeString(this.chassis);
        dest.writeValue(this.vehicleId);
        dest.writeValue(this.type);
        dest.writeValue(this.brand);
        dest.writeValue(this.model);
        dest.writeInt(this.fabricationYear);
        dest.writeInt(this.cylinderCapacity);
        dest.writeValue(this.specialFunction);
        dest.writeValue(this.vehicleAction);
    }

    public void readFromParcel(Parcel source) {
        this.vehicleAccidentNumber = (Long) source.readValue(Long.class.getClassLoader());
        this.plate = source.readString();
        this.chassis = source.readString();
        this.vehicleId = (Long) source.readValue(Long.class.getClassLoader());
        this.type = (Long) source.readValue(Long.class.getClassLoader());
        this.brand = (Long) source.readValue(Long.class.getClassLoader());
        this.model = (Long) source.readValue(Long.class.getClassLoader());
        this.fabricationYear = source.readInt();
        this.cylinderCapacity = source.readInt();
        this.specialFunction = (Long) source.readValue(Long.class.getClassLoader());
        this.vehicleAction = (Long) source.readValue(Long.class.getClassLoader());
    }

    public VehiculeReq() {
    }

    public VehiculeReq(Parcel in) {
        this.vehicleAccidentNumber = (Long) in.readValue(Long.class.getClassLoader());
        this.plate = in.readString();
        this.chassis = in.readString();
        this.vehicleId = (Long) in.readValue(Long.class.getClassLoader());
        this.type = (Long) in.readValue(Long.class.getClassLoader());
        this.brand = (Long) in.readValue(Long.class.getClassLoader());
        this.model = (Long) in.readValue(Long.class.getClassLoader());
        this.fabricationYear = in.readInt();
        this.cylinderCapacity = in.readInt();
        this.specialFunction = (Long) in.readValue(Long.class.getClassLoader());
        this.vehicleAction = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<VehiculeReq> CREATOR = new Parcelable.Creator<VehiculeReq>() {
        @Override
        public VehiculeReq createFromParcel(Parcel source) {
            return new VehiculeReq(source);
        }

        @Override
        public VehiculeReq[] newArray(int size) {
            return new VehiculeReq[size];
        }
    };
}
