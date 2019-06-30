package project.test;

import project.user.*;
import project.application.*;
import project.interview.*;
import project.observer.*;
import project.system.*;
import project.utils.CSVReader;
import project.utils.CSVTable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();
        Company company = new Company("UofT", system);
        JobPosting jobPosting = new JobPosting(new Job("a", company), null, null,
                new BasicRequirement(), 1);
        company.getJobPostingManager().addJobPosting(jobPosting);



        try {
            String content = new String(Files.readAllBytes(Paths.get(
                    "C:\\Users\\xiaoc\\Downloads\\FL_insurance_sample.csv"
            )), StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader((content));
            CSVTable table = reader.parse();
            System.out.println("End");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
