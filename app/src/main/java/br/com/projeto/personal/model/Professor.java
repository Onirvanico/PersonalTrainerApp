package br.com.projeto.personal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Professor implements Parcelable {

    private String nome;
    private String experiencia;
    private int reputação;

    public Professor(String nome, String experiencia) {
        this.nome = nome;
        this.experiencia = experiencia;
    }

    protected Professor(Parcel in) {
        nome = in.readString();
        experiencia = in.readString();
        reputação = in.readInt();
    }

    public static final Creator<Professor> CREATOR = new Creator<Professor>() {
        @Override
        public Professor createFromParcel(Parcel in) {
            return new Professor(in);
        }

        @Override
        public Professor[] newArray(int size) {
            return new Professor[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public String getExperiencia() {
        return experiencia;
    }


    public int getReputação() {
        return reputação;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(experiencia);
        dest.writeInt(reputação);
    }
}
