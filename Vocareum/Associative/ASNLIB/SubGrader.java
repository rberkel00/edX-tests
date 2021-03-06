 
import java.lang.Integer;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import voc.grader.*;
import java.lang.Math;

public class SubGrader {

	static String[] GROUPS = {
		"completion",
		"correctness",
		"style",
		"efficiency",
		"extracredit"
	};

	static double[] totals;

	static Test[] tests = {
		new Test("Associative.java file exists", 0, 1), //0
		new Test("Associative class header", 0, 1),  //1
		new Test("Main method signature", 0, 1), //2
		new Test("firstTwo method signature", 0, 1), //3
		new Test("lastTwo method signature", 0, 1), //4
		new Test("Main method body creates a Scanner object", 0, 1), //5
		new Test("Main method prompts the user for input", 0, 1), //6
		new Test("Main method creates new Associative object", 0, 1), //7
		new Test("Main method stores integers from standard input", 0, 1), //8
		new Test("Main method printed statement", 0, 2), //9
		new Test("Created 3 integer class variables", 0, 1), //10
		new Test("firstTwo and lastTwo return statements exist", 0, 1), //11
		new Test("Constructor signature", 0, 1), //12
		new Test("Blocks are clearly defined", 2, 1), //13
		new Test("Correct output: prompts user", 1, 1), //14
		new Test("Correct output: firstTwo", 1, 2), //15
		new Test("Correct output: lastTwo", 1, 1), //16
		new Test("Associative.java compiles", 0, 1), //17
		new Test("Used camel casing for methods and classes", 2, 1), //18
		new Test("One statement per line", 2, 1) //19
	};

	public static void main(String[] args) {

		double[] totals = new double[5];
		for (int i = 0; i < totals.length; i++) {
			totals[i] = 0.0;
		}
		String line;
		int testnum;

		try {

			File score = new File(args[0] + "/score.txt");
			Scanner read = new Scanner(score);

			while(read.hasNext()) {
				line = read.nextLine();
				testnum = Integer.parseInt(line.substring(0, line.indexOf(":")));
				if (line.contains("true")) {
					tests[testnum].passed = true;
					totals[tests[testnum].group] += tests[testnum].point;
				}
			}

			for (int i = 0; i < tests.length; i++) {
				if (!tests[i].passed) {
					System.out.println("Test Failed: " + tests[i].message);
				}
			}

			double finaltotal = 0;
			System.out.println();

			for (int i = 0; i < GROUPS.length; i++) {
				System.out.println(GROUPS[i] + ": " + totals[i]);
				finaltotal+=totals[i];
			}

			System.out.println("total: " + finaltotal);

		} catch (FileNotFoundException e) {
			System.out.println("Score.txt not found");
		}
		System.out.println();
      
      	//Vocareum Grader
      	Grader g = new Grader();
      	g.setReportOutput(1);
      	GradeInfo gi = new GradeInfo("Completion", (int)Math.round(totals[0]));
      	g.addGrade(gi);
      	gi = new GradeInfo("Correctness", (int)Math.round(totals[1]));
      	g.addGrade(gi);      
      	gi = new GradeInfo("Style", (int)Math.round(totals[2]));
      	g.addGrade(gi);
	}
  
    public static class Test {
  
      public String message;
      public double point;
      public int group;
      public boolean passed;
  
      public Test (String message, int group, double point) {
          this.message = message;
          this.group = group;
          this.point = point;
          passed = false;
      }
  	}
}

