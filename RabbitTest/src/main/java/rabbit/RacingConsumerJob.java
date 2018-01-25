package rabbit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

/**
 * Created by adi on 1/24/18.
 */
public class RacingConsumerJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("Job Starts at " + new Date());

        ConsumerManager consumerManager = (ConsumerManager) jobExecutionContext.getJobDetail().getJobDataMap().get(RacingConsumerTest.CONSUMER_MANAGER_JOB_DATA_KEY);

//        System.out.println("isrebuildcancelled:"+consumerManager.isRebuildOnJoinCancelled());

        boolean rebuildOnJoinCancelled = consumerManager.isRebuildOnJoinCancelled();
        if(rebuildOnJoinCancelled){
            consumerManager.resumeCancelledRebuildOnJoin();
            System.out.println("isrebuildcancelled after cancel:"+consumerManager.isRebuildOnJoinCancelled());
        }

        System.out.println("lastRebuildList:"+ Rebuilder.lastRebuildList.get());

        Map<String, String> currentConsumerDetails = consumerManager.getCurrentConsumerDetails();
//        consumerManager.updateConsumerDetails(currentConsumerDetails, true, !rebuildOnJoinCancelled);
        consumerManager.updateConsumerDetails(currentConsumerDetails, Rebuilder.rebuilder.get() == null,
                !(Rebuilder.isLastRebuildCancelled.get() || Rebuilder.lastRebuildList.get().isEmpty()));
//        System.out.println("Job Ends at " + new Date());

    }
}
