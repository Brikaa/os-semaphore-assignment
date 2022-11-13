package primewriter.jobs;

import primewriter.gui.*;

public class WriteLabelJob implements ConsumptionJob{
    TxtField l1;
    public WriteLabelJob (){ 
        l1 = new TxtField();
    }

    public void initiate()throws JobException{;};
    public void cleanup() throws JobException{;};

    public void run (Object message) throws JobException{
    try {
        l1.createfield(message.toString());
    } catch (Exception e) {
        throw new JobRunException ("Can't write to text field ");
      }
    System.out.println(message);
    }

}



