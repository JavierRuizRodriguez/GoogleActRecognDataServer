package com.googleActRecognDataServer.api.cronos;

import org.rosuda.JRI.Rengine;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * Class where background server tasks are set.
 * 
 * @author AceroDocs
 *
 */

@Configuration
@EnableScheduling
public class CronosTasks {

	//The command-line option --vanilla implies --no-site-file, --no-init-file, --no-environ and (except for R CMD) --no-restore
	private static final Rengine re = new Rengine(new String[] { "-vanilla -no-save" }, false, null);

	//se lanza al comienzo de cada día
	@Scheduled(cron = "0 0 0 * * *", zone = "Europe/Madrid")
	public void realizarAnalisisEstadistico() {
		String javaVector = "c(1,2,3,4,5)";

		if (!re.waitForR())
        {
            System.out.println ("Cannot load R");
        }

		// The vector that was created in JAVA context is stored in 'rVector'
		// which is a variable in R context.
		re.eval("rVector=" + javaVector);

		// Calculate MEAN of vector using R syntax.
		re.eval("meanVal=mean(rVector)");

		// Retrieve MEAN value
		double mean = re.eval("meanVal").asDouble();

		// Print output values
		System.out.println("Mean of given vector is= " + mean);
	}

}
