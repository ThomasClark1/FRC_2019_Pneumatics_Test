/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;





public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  int joystickPort = 0;
  Joystick m_joystick = new Joystick(joystickPort);
  int btnActuateSolenoid = 0;
  boolean rodExtended = false;

  //Forward Channel Number for the double solenoid:
  int forwardCN = 0;
  //Reverse Channel Number for the double solenoid:
  int reverseCN = 0;
  //(PCMNodeCN stands for "Pneumatics Control Module Node Channel Number"):
  int PCMNodeCN = 0;
  //For more info on the DoubleSolenoid class, go to this url: https://wpilib.screenstepslive.com/s/currentCS/m/cpp/l/241866-operating-pneumatic-cylinders-solenoids
  DoubleSolenoid m_solenoid = new DoubleSolenoid(forwardCN, reverseCN);
  //For more info on the compressor class, go to this url: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599707-operating-a-compressor-for-pneumatics
  Compressor m_compressor = new Compressor(PCMNodeCN);





  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    //Turns the compressor on:
    m_compressor.setClosedLoopControl(true);
    //Makes sure the robot starts with its rod hidden
    m_solenoid.set(DoubleSolenoid.Value.kReverse);
    SmartDashboard.putData("Auto choices", m_chooser);
  }





  @Override
  public void robotPeriodic() {

  }


  


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

 
  


  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }


  


  @Override
  public void teleopPeriodic() {

    //Code for commencing rod extendus and rod retractus
    if (m_joystick.getRawButtonPressed(btnActuateSolenoid)) {

      if (rodExtended = false) {
        m_solenoid.set(DoubleSolenoid.Value.kForward);
        rodExtended = true;
      } else {
        m_solenoid.set(DoubleSolenoid.Value.kReverse);
        rodExtended = false;
      }

    }
    
    //SmartDashboard things for looking at the numbers and stuff.
    SmartDashboard.putBoolean("compressor enabled/disabled: ", m_compressor.enabled());
    SmartDashboard.putBoolean("compressor pressure switch status: ", m_compressor.getPressureSwitchValue());
    SmartDashboard.putNumber("compressor current value: ", m_compressor.getCompressorCurrent());
  }

  



  @Override
  public void testPeriodic() {

  }
}
