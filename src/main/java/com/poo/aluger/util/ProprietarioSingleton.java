package com.poo.aluger.util;

import com.poo.aluger.model.Proprietario;

public class ProprietarioSingleton {
  private static ProprietarioSingleton instance = null;
  private Proprietario proprietario;

  private ProprietarioSingleton() {
  }

  public static ProprietarioSingleton getInstance() {
    if (instance == null) {
      instance = new ProprietarioSingleton();
    }
    return instance;
  }

  public void setProprietario(Proprietario proprietario) {
    this.proprietario = proprietario;
  }

  public Proprietario getProprietario() {
    return this.proprietario;
  }
}
