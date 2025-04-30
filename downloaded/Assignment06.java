//package app;
//
//import com.scg.beans.BenefitManager;
//import com.scg.beans.CompensationManager;
//import com.scg.beans.Eeoc;
//import com.scg.beans.HumanResourceManager;
//import com.scg.beans.StaffConsultant;
//import com.scg.util.PersonalName;
//
//public class Assignment06
//{
//    public static void main(String[] args)
//    {
//        new Assignment06().execute();
//
//    }
//
//    public void execute()
//    {
//        PersonalName    name1   = new PersonalName( "Einstein", "Albert" );
//        PersonalName    name2   = new PersonalName( "Antoinette", "Marie" );
//        PersonalName    name3   = new PersonalName( "Schlafly", "Phyllis" );
//
//        int             pay1    = 4000;
//        int             pay2    = 6000;
//        int             pay3    = 7000;
//
//        StaffConsultant cons1   = new StaffConsultant( name1, pay1, 10, 20 );
//        StaffConsultant cons2   = new StaffConsultant( name2, pay2, 10, 20 );
//        StaffConsultant cons3   = new StaffConsultant( name3, pay3, 10, 20 );
//
//        BenefitManager          benMgr  = new BenefitManager();
//        HumanResourceManager    hrMgr   = new HumanResourceManager();
//        CompensationManager     comMgr  = new CompensationManager();
//        Eeoc                    eeoc    = new Eeoc();
//
//        hrMgr.addBenefitListener( benMgr );
//        hrMgr.addTerminationListener( eeoc );
//
//        cons1.addPropertyChangeListener( benMgr );
//        cons1.addVetoableChangeListener( comMgr );
//
//        cons2.addPropertyChangeListener( benMgr );
//        cons2.addVetoableChangeListener( comMgr );
//
//        cons1.addPropertyChangeListener( benMgr );
//        cons1.addVetoableChangeListener( comMgr );
//
//        hrMgr.adjustPayRate( cons1, (int)(cons1.getPayRate() * 1.04) );
//        hrMgr.adjustPayRate( cons2, (int)(cons2.getPayRate() * 1.10) );
//
//        hrMgr.adjustSickLeaveHours( cons1, 20 );
//        hrMgr.adjustVacationHours( cons1, 40 );
//
//        hrMgr.enrollDental( cons1 );
//        hrMgr.enrollMedical( cons1 );
//
//        hrMgr.enrollDental( cons2 );
//        hrMgr.enrollMedical( cons2 );
//
//        hrMgr.enrollDental( cons3 );
//        hrMgr.enrollMedical( cons3 );
//
//        hrMgr.acceptResignation( cons1 );
//        hrMgr.cancelDental( cons1 );
//        hrMgr.cancelMedical( cons1 );
//
//        hrMgr.terminate( cons2 );
//        hrMgr.cancelDental( cons2 );
//        hrMgr.cancelMedical( cons2 );
//    }
//}
