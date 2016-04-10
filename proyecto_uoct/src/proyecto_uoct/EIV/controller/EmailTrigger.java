package proyecto_uoct.EIV.controller;
import proyecto_uoct.proyecto.controller.MailAutoVenceOT;
import org.quartz.JobDetail;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SimpleTrigger;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.quartz.Scheduler;
import java.util.Date;
import org.quartz.helpers.TriggerUtils;
import org.quartz.SchedulerFactory;
import proyecto_uoct.EIV.controller.MailAutoJob;
import org.quartz.Trigger;
import org.quartz.CronTrigger;
import proyecto_uoct.proyecto.controller.MailAutoVenceOT;


public class EmailTrigger {
    public EmailTrigger() {
    }

    public void run() throws Exception {
           Log log = LogFactory.getLog(EmailTrigger.class);

           log.info("------- Iniciando ----------------------");

           // Obtenemos una referencia al scheduler
           SchedulerFactory sf = new StdSchedulerFactory();
           Scheduler sched = sf.getScheduler();
           Scheduler schedOT = sf.getScheduler();

           log.info("------- Inicio completado -----------");

           log.info("------- Scheduling Jobs -------------------");

           // computer a time that is on the next round minute
           Date runTime = TriggerUtils.getEvenMinuteDate(new Date());

           // define the job and tie it to our class with the job
           JobDetail job = new JobDetail("envioEmail", "groupJobs", MailAutoJob.class);

           // Trigger the job to run on the next round minute
           //SimpleTrigger trigger =
            //new SimpleTrigger("emailTrigger", "groupTrigger1", runTime);

            CronTrigger trigger = new CronTrigger("emailTrigger2", "groupTrigger2", job.getName(), job.getGroup(),
                                                  "0 30 12 * * ?"); // se gatilla a las 12:30 PM cada día.


            JobDetail jobOT= new JobDetail("envioEmailVencimientoOT","groupJobs", MailAutoVenceOT.class);


            CronTrigger triggerOT = new CronTrigger("emailTriggerOT", "groupTriggerOT", jobOT.getName(), jobOT.getGroup(),
                                                  "0 00 09 * * ?"); // se gatilla a las 09:00 PM cada día.


           // sched.addJob(job, true);


           //Trigger trigger=TriggerUtils.makeDailyTrigger(0,52);

           // Tell quartz to schedule the job using our trigger
           sched.scheduleJob(job, trigger);
           schedOT.scheduleJob(jobOT, triggerOT);

           // Start up the scheduler (nothing can actually run until the
           // scheduler has been started)
           sched.start();
           schedOT.start();
           log.info("------- Started Scheduler -----------------");

           // wait long enough so that the scheduler as an opportunity to
           // run the job!
          // log.info("------- esperando 60 segundos... -------------");
           //try {
               // wait 90 seconds to show jobs
             //  Thread.sleep(60L * 1000L);
               // executing...
           //} catch (Exception e) {
           //}

           // shut down the scheduler
          // log.info("------- Shutting Down ---------------------");
           //sched.shutdown(true);
           //log.info("------- Shutdown Complete -----------------");
       }

       public static void main(String[] args) throws Exception {

           EmailTrigger em = new EmailTrigger();
           em.run();

   }

}
