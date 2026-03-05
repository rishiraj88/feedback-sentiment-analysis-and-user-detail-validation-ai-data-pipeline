package rr.userdetails.userbatch;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameter;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Configuration for command-line runner to execute the job upon app startup
 */
@Configuration @EnableBatchProcessing
public class CommandLineConfig {
    /**
     * Construct the command liner runner
     * @param jobOperator the job launcher
     * @param job the Spring Batch job to start
     * @return the line runner
     */
    @Bean
    CommandLineRunner jobRunner(@Qualifier("batchJobLauncher") JobOperator jobOperator, Job job){Set<JobParameter<?>> parameterSet = new HashSet<JobParameter<?>>();
        parameterSet.add(new JobParameter<String>("time", System.currentTimeMillis() + "", String.class));
        JobParameters parameters = new JobParameters(parameterSet);
        return args -> jobOperator.start(job, parameters);
    }
}
