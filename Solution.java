package com.codegym.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2

*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Donald Chump", new Date()));  // id=0
        allPeople.add(Person.createMale("Larry Gates", new Date()));  // id=1
    }

    public static void main(String[] args) throws ParseException {
        // Start here
        if (args.length > 0) {
            switch (args[0]) {
                case "-c":
                    synchronized (allPeople) {
                        for (int i = 1; i <= ((args.length - 1) / 3); i++) {
                            SimpleDateFormat sf1 = new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH);
                            Date dbdate2 = sf1.parse(args[3 * i]);

                            if (args[3 * i - 1].equals("m")) {
                                Person newone = Person.createMale(args[3 * i - 2], dbdate2);
                                allPeople.add(newone);
                                System.out.println(allPeople.indexOf(newone));

                            } else if (args[3 * i - 1].equals("f")) {
                                Person newone = Person.createFemale(args[3 * i - 2], dbdate2);
                                allPeople.add(newone);
                                System.out.println(allPeople.indexOf(newone));

                            }
                        }
                    }

                    break;
                case "-u":
                    synchronized (allPeople) {
                        for (int i = 1; i <= ((args.length - 1) / 4); i++) {
                            SimpleDateFormat sf1 = new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH);
                            Date dbdate2 = sf1.parse(args[4 * i]);

                            Person per = allPeople.get(Integer.parseInt(args[4 * i - 3]));
                            per.setName(args[4 * i - 2]);
                            per.setBirthDate(dbdate2);

                            if (args[4 * i - 1].equals("m")) {
                                per.setSex(Sex.MALE);
                            } else if (args[4 * i - 1].equals("f")) {
                                per.setSex(Sex.FEMALE);
                            }
                        }
                    }
                    break;
                case "-d":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Person per = allPeople.get(Integer.parseInt(args[i]));
                            per.setName(null);
                            per.setBirthDate(null);
                            per.setSex(null);
                        }
                    }

                    break;
                case "-i":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Person per = allPeople.get(Integer.parseInt(args[i]));
                            String res = per.getName();
                            SimpleDateFormat sf1 = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
                            SimpleDateFormat sf2 = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
                            Date date = per.getBirthDate();
                            String datest = sf1.format(date);
                            Date dateresults = sf1.parse(datest);
                            String datafinaly = sf2.format(dateresults);
                            if (per.getSex() == Sex.MALE) {
                                res = res + " m " + datafinaly;
                                System.out.println(res);
                            } else if (per.getSex() == Sex.FEMALE) {
                                res = res + " f " + datafinaly;
                                System.out.println(res);
                            }
                        }
                    }
                    break;
            }
        }
    }
}
