package es.iessaladillo.pedrojoya.pr01.bmi;

/**
 * Allow Bmi calculation and clasification
 */
public class BmiCalculator {

    /**
     * @param weightInKgs Weight of the person in kgs
     * @param heightInMeters Height of the person in meters
     * @return The body mass index (BMI)
     */
    public float calculateBmi(float weightInKgs, float heightInMeters) {

        if (heightInMeters <= 0 || weightInKgs <= 0) {

            throw new IllegalArgumentException();

        }

        return (float)(weightInKgs/Math.pow(heightInMeters, 2));
    }


    /**
     * @param bmi Body mass index to get clasification from
     * @return A BmiClasification enum with the clasification of BMI
     */
    public BmiClasification getBmiClasification(float bmi) {

        BmiClasification classification;

        if (bmi < 18.5) {

            classification = BmiClasification.LOW_WEIGHT;

        }

        else if (bmi >= 18.5 && bmi < 25) {

            classification = BmiClasification.NORMAL_WEIGHT;

        }

        else if (bmi >= 25 && bmi < 30) {

            classification = BmiClasification.OVERWWEIGHT;

        }

        else if (bmi >= 30 && bmi < 35) {

            classification = BmiClasification.OBESITY_GRADE_1;

        }

        else if (bmi >= 35 && bmi < 40) {

            classification = BmiClasification.OBESITY_GRADE_2;

        }

        else {

            classification = BmiClasification.OBESITY_GRADE_3;

        }

        return classification;
    }

    public enum BmiClasification {
        LOW_WEIGHT, NORMAL_WEIGHT, OVERWWEIGHT, OBESITY_GRADE_1, OBESITY_GRADE_2, OBESITY_GRADE_3
    }
}
