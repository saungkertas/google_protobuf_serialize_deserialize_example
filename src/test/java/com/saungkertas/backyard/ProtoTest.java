package com.saungkertas.backyard;

import com.saungkertas.backyard.protobuf.AddressBookProtos;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ProtoTest {
    private final String filePath = "out_serialized/address_book";

    @Test
    public void foo() throws IOException {
        String email = "syarif.saungkertas@gmail.com";
        int id = 68098098;
        String name = "Syarif Hidayatullah";
        String number = "989982933";
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setEmail(email)
                .setId(id)
                .setName(name)
                .addNumbers(number)
                .build();

        //when
        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(person).build();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        addressBook.writeTo(fileOutputStream);
        fileOutputStream.close();

        //then
        FileInputStream fileInputStream = new FileInputStream(filePath);
        AddressBookProtos.AddressBook deserialized = AddressBookProtos.AddressBook.parseFrom(fileInputStream);
        fileInputStream.close();
        Assert.assertEquals(deserialized.getPeople(0).getId(), id);
    }
}
