/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carolsboutique.clientpos.email.model;

import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nicad
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Email {
    private String id;
    private String address;
    private String message;
    private Date dateSent;
    private Time timeSent;
}
