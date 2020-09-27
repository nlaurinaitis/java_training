package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

    @Parameter(names = "-c", description = "User count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("csv")) {
            saveAsCsv(users, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(users, new File(file));
        } else {
            System.out.println("Unrecognised format " + format + ".");
        }
    }

    private void saveAsCsv(List<UserData> users, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (UserData user : users) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s\n", user.getFirstName(), user.getLastName(),
                    user.getHomeNumber(), user.getEmail(), user.getAddress(), user.getGroup()));
        }
        writer.close();
    }

    private void saveAsXml(List<UserData> users, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        String xml = xstream.toXML(users);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<UserData>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData().withFirstName(String.format("Harry%s", i))
                    .withLastName(String.format("Potter%s", i))
                    .withHomeNumber(String.format("347273%s", i))
                    .withEmail(String.format("hp%s@test.com", i))
                    .withAddress(String.format("UK, London, Privet Drive, %s", i))
                    .withGroup(String.format("test %s", i)));
        }
        return users;
    }
}
