package br.com.projeto.personal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Professor implements Parcelable {

    private String _id;
    private String nome;
    private String experiencia;
    private int reputação;

    public Professor(String nome, String experiencia) {
        this.nome = nome;
        this.experiencia = experiencia;
    }

    protected Professor(Parcel in) {
        _id = in.readString();
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

    public void setReputação(int reputação) {
        this.reputação = reputação;
    }

    public String getId() {
        return _id;
    }

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
        return this.nome + "/id: " + this._id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(nome);
        dest.writeString(experiencia);
        dest.writeInt(reputação);
    }
}
