package br.mack.persistencia;

import br.mack.entidade.HeartRate;


public interface HeartRateDAO {
   boolean create(HeartRate heartRate);
    boolean delete(int opc);
    boolean update(HeartRate heartRate);
    void read();
}
