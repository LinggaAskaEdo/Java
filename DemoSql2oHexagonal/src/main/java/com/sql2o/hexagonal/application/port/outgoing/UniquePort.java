package com.sql2o.hexagonal.application.port.outgoing;

public interface UniquePort
{
    boolean save(String id);
    Integer get(String id);
}