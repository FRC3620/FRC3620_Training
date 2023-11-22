package frc.robot;

import java.util.Date;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;
import org.usfirst.frc3620.misc.GitNess;
import org.usfirst.frc3620.misc.RobotMode;
import edu.wpi.first.networktables.DoubleEntry;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private Logger logger;

  static private RobotMode currentRobotMode = RobotMode.INIT, previousRobotMode;

  //DoubleEntry aEntry, bEntry, xEntry; //This just is easier for making multiple things of the same type. But meh.
DoubleEntry aEntry;
DoubleEntry bEntry;
DoubleEntry xEntry;


  Date dateAtInitialization = new Date();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    logger = EventLogging.getLogger(Robot.class, Level.INFO);
    logger.info ("I'm alive! {}", GitNess.gitDescription());

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    //The network table creates a topic called "SmartDashboard" with a value of "a" so that I can get an entry of it and put it in there.
    aEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/a").getEntry(0.0); //
    bEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/b").getEntry(0.0);
    xEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/x").getEntry(0.0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    double aEntryValue = aEntry.get(1);
    double bEntryValue = bEntry.get(1);
    double xEntryValue = aEntryValue + bEntryValue;
    xEntry.get(xEntryValue);
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    processRobotModeChange(RobotMode.DISABLED);
  }

  @Override
  public void disabledPeriodic() {
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    processRobotModeChange(RobotMode.AUTONOMOUS);

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    processRobotModeChange(RobotMode.TELEOP);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    processRobotModeChange(RobotMode.TEST);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }

  /*
  * this routine gets called whenever we change modes
  */
  void processRobotModeChange(RobotMode newMode) {
    previousRobotMode = currentRobotMode;
    currentRobotMode = newMode;

    // if any subsystems need to know about mode changes, let
    // them know here.
    // exampleSubsystem.processRobotModeChange(newMode);
  }

  public static RobotMode getCurrentRobotMode(){
    return currentRobotMode;
  }

  public static RobotMode getPreviousRobotMode(){
    return previousRobotMode;
  }
}