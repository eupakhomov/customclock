package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing stories.  
 * You will notice the TimeConverter has no implementation ... (hint)
 */
public class BerlinClockFixture implements TimeUnitsSpec {

    private TimeConverter berlinClock;
    private String theTime;
     
    @BeforeStory
    public void initialize() {
    	
    	IndicatorsRow[] rows = new IndicatorsRow[5];
    	rows[0] = new BlinkingIndicatorRow(2, 'Y');
    	rows[1] = new ChainedIndicatorsRow(HOURS_DIVISOR * 5, 4, 'R');	
    	rows[2] = new ChainedIndicatorsRow(HOURS_DIVISOR, 4, 'R');
    	rows[3] = new ChainedIndicatorsRow(MINS_DIVISOR * 5, 11, 'Y', 'R', 3);
    	rows[4] = new ChainedIndicatorsRow(MINS_DIVISOR, 4, 'Y');
    	
    	berlinClock = new TimeConverterImpl(rows, "\r\n");
    }

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
    }
    
    @Then("the application should return error message $") 
    public void thenTheApplicationShouldReturnErrorMessage(String theExpectedErrorMessage) {
    	try {
    		berlinClock.convertTime(theTime);
        } catch (IllegalArgumentException ex) {
        	assertThat(ex.getMessage()).isEqualTo(theExpectedErrorMessage);
            return;
        }
    	
    	failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
    }
}
