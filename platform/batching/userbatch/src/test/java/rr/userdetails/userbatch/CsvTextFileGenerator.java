package rr.userdetails.userbatch;

import nyla.solutions.core.io.IO;
import nyla.solutions.core.io.csv.CsvWriter;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import nyla.solutions.core.util.Config;
import rr.userdetails.userbatch.domain.User;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Generate user records
 */
public class CsvTextFileGenerator {

    public static void main(String[] args) throws IOException {

        var userCreator = JavaBeanGeneratorCreator.of(User.class);

        var settings = Config.settings();

        var file = Paths.get(settings.getProperty("file","platform/batching/user-batch/src/test/resources/datasource/userdetails.csv")).toFile();
        IO.delete(file);
        var csvWriter = new CsvWriter(file);

        //id,first_name,last_nm,email,phone,address line,city,state,zip
        var count = settings.getPropertyInteger("count",100);

        var user = userCreator.create();
        //missing firstname and lastname and email
        //seconds missing phone and email
        csvWriter.appendRow(
                "",
                "",
                "",
                "",
                "",
                user.location().address(),
                user.location().city(),
                user.location().state(),
                user.location().zip()
        );


        user = userCreator.create();
        //seconds missing phone and email
        csvWriter.appendRow(
                "",
                "",
                "",
                "",
                "",
                user.location().address(),
                user.location().city(),
                user.location().state(),
                user.location().zip()
        );

        //all records have all required fields
        for (int i = 2; i < count; i++) {
            user = userCreator.create();
                csvWriter.appendRow(
                        user.contact().email(),
                        user.firstName(),
                        user.lastName(),
                        user.contact().email(),
                        user.contact().phone(),
                        user.location().address(),
                        user.location().city(),
                        user.location().state(),
                        user.location().zip()
                );
            }
    }
}
