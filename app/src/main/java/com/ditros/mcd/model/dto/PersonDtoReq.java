package com.ditros.mcd.model.dto;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDtoReq implements Parcelable {
    private int personAccidentNumber;
    private Long vehicleAccidentNumber;

    private String firstName;

    private String lastName;
    private int vehicleLinkedPedestrian;
    private String birthDate;
    private Long gender;
    private Long roadType;
    private Long range;
    private int care;
    private Long place;
    private Long id;
    private Long traumaSeverity;
    private Long wearingHelmet;
    private Long occupantRestraintSystem;
    private Long personAction;
    private Long alcoholConsumption;
    private Long testStatus;
    private Long testType;
    private Long testResult;
    private Long drugUse;
    private String drivingLicenceYear;

    private String vehicleid;




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.personAccidentNumber);
        dest.writeLong(this.vehicleAccidentNumber);
        dest.writeInt(this.vehicleLinkedPedestrian);
        dest.writeString(this.birthDate);
        dest.writeValue(this.gender);
        dest.writeValue(this.roadType);
        dest.writeValue(this.range);
        dest.writeInt(this.care);
        dest.writeValue(this.place);
        dest.writeValue(this.id);
        dest.writeValue(this.traumaSeverity);
        dest.writeValue(this.wearingHelmet);
        dest.writeValue(this.occupantRestraintSystem);
        dest.writeValue(this.personAction);
        dest.writeValue(this.alcoholConsumption);
        dest.writeValue(this.testStatus);
        dest.writeValue(this.testType);
        dest.writeValue(this.testResult);
        dest.writeValue(this.drugUse);
        dest.writeString(this.drivingLicenceYear);
        dest.writeString(this.vehicleid);

    }

    public void readFromParcel(Parcel source) {
        this.personAccidentNumber = source.readInt();
        this.vehicleAccidentNumber = source.readLong();
        this.vehicleLinkedPedestrian = source.readInt();
        this.birthDate = source.readString();
        this.gender = (Long) source.readValue(Long.class.getClassLoader());
        this.roadType = (Long) source.readValue(Long.class.getClassLoader());
        this.range = (Long) source.readValue(Long.class.getClassLoader());
        this.care = source.readInt();
        this.place = (Long) source.readValue(Long.class.getClassLoader());
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.traumaSeverity = (Long) source.readValue(Long.class.getClassLoader());
        this.wearingHelmet = (Long) source.readValue(Long.class.getClassLoader());
        this.occupantRestraintSystem = (Long) source.readValue(Long.class.getClassLoader());
        this.vehicleid = (String) source.readValue(Long.class.getClassLoader());
        this.personAction = (Long) source.readValue(Long.class.getClassLoader());
        this.alcoholConsumption = (Long) source.readValue(Long.class.getClassLoader());
        this.testStatus = (Long) source.readValue(Long.class.getClassLoader());
        this.testType = (Long) source.readValue(Long.class.getClassLoader());
        this.testResult = (Long) source.readValue(Long.class.getClassLoader());
        this.drugUse = (Long) source.readValue(Long.class.getClassLoader());
        this.drivingLicenceYear = source.readString();
    }

    protected PersonDtoReq(Parcel in) {
        this.personAccidentNumber = in.readInt();
        this.vehicleAccidentNumber = in.readLong();
        this.vehicleid = in.readString();
        this.vehicleLinkedPedestrian = in.readInt();
        this.birthDate = in.readString();
        this.gender = (Long) in.readValue(Long.class.getClassLoader());
        this.roadType = (Long) in.readValue(Long.class.getClassLoader());
        this.range = (Long) in.readValue(Long.class.getClassLoader());
        this.care = in.readInt();
        this.place = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.traumaSeverity = (Long) in.readValue(Long.class.getClassLoader());
        this.wearingHelmet = (Long) in.readValue(Long.class.getClassLoader());
        this.occupantRestraintSystem = (Long) in.readValue(Long.class.getClassLoader());
        this.personAction = (Long) in.readValue(Long.class.getClassLoader());
        this.alcoholConsumption = (Long) in.readValue(Long.class.getClassLoader());
        this.testStatus = (Long) in.readValue(Long.class.getClassLoader());
        this.testType = (Long) in.readValue(Long.class.getClassLoader());
        this.testResult = (Long) in.readValue(Long.class.getClassLoader());
        this.drugUse = (Long) in.readValue(Long.class.getClassLoader());
        this.drivingLicenceYear = in.readString();
    }

    public static final Creator<PersonDtoReq> CREATOR = new Creator<PersonDtoReq>() {
        @Override
        public PersonDtoReq createFromParcel(Parcel source) {
            return new PersonDtoReq(source);
        }

        @Override
        public PersonDtoReq[] newArray(int size) {
            return new PersonDtoReq[size];
        }
    };
}
