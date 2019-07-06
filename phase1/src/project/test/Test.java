//package project.test;
//
//import project.user.*;
//import project.application.*;
//import project.interview.*;
//import project.observer.*;
//import project.system.*;
//import project.utils.*;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class Test {
//    public static void main(String[] args) {
//        MainSystem system = new MainSystem();
//        Company company = new Company("UofT", system);
//        JobPosting jobPosting = new JobPosting(new Job("a", company), null, null,
//                new BasicRequirement(), 1);
//        company.getJobPostingManager().addJobPosting(jobPosting);
//
//
//
//        try {
//            String content = new String(Files.readAllBytes(Paths.get(
//                    "C:\\Users\\xiaoc\\Downloads\\FL_insurance_sample2.csv"
//            )), StandardCharsets.UTF_8);
//            CSVReader reader = new CSVReader((content));
//            CSVTable table = reader.parse();
//            CSVWriter writer = new CSVWriter(table.getKeys());
//            for(CSVRecord record: table){
//                CSVRecord r = writer.newRecord();
//                for(CSVItem item : record){
//                    r.put(item.getValue());
//                }
//                writer.addRecord(r);
//            }
//            String out = writer.toString();
//            System.out.println(out);
//            PrintWriter printWriter = new PrintWriter("C:\\Users\\xiaoc\\Downloads\\FL_insurance_sample3.csv");
//            printWriter.print(out);
//            printWriter.flush();
//            printWriter.close();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}
