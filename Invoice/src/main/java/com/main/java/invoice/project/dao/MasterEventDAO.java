package com.main.java.invoice.project.dao;

import com.main.java.invoice.project.pojo.MasterDana;
import com.main.java.invoice.project.pojo.MasterEvent;
import com.main.java.invoice.project.preference.StaticPreference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dery on 2/24/18.
 */
public class MasterEventDAO
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUpdate(MasterEvent masterEvent, int flag)
    {
        String query = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            if (flag == 0)
            {
                query = "INSERT INTO MASTER_EVENT(AGENT_EVENT, NAME, ADDRESS, NO_NPWP, INFORMATION)" +
                        "VALUES (?, ?, ?, ?, ?)";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterEvent.getAgentEvent());
                preparedStatement.setString(2, masterEvent.getName());
                preparedStatement.setString(3, masterEvent.getAddress());
                preparedStatement.setString(4, masterEvent.getNoNpwp());
                preparedStatement.setString(5, masterEvent.getInformation());
            }
            else
            {
                query = "UPDATE MASTER_EVENT set AGENT_EVENT = ?, NAME = ?, ADDRESS = ?, NO_NPWP = ?, INFORMATION = ?" +
                        "WHERE MASTER_EVENT_ID = ?";

                preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, masterEvent.getAgentEvent());
                preparedStatement.setString(2, masterEvent.getName());
                preparedStatement.setString(3, masterEvent.getAddress());
                preparedStatement.setString(4, masterEvent.getNoNpwp());
                preparedStatement.setString(5, masterEvent.getInformation());
                preparedStatement.setInt(6, masterEvent.getMasterEventId());
            }

            preparedStatement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close();
        }

    }

    public List<MasterEvent> GetAllMasterEvent ()
    {
        List<MasterEvent> allMasterEvent = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT MASTER_EVENT_ID, AGENT_EVENT, NAME, ADDRESS, NO_NPWP, INFORMATION FROM MASTER_EVENT";

            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                MasterEvent masterEvent = new MasterEvent();
                masterEvent.setMasterEventId(resultSet.getInt(1));
                masterEvent.setAgentEvent(resultSet.getString(2));
                masterEvent.setName(resultSet.getString(3));
                masterEvent.setAddress(resultSet.getString(4));
                masterEvent.setNoNpwp(resultSet.getString(5));
                masterEvent.setInformation(resultSet.getString(6));

                allMasterEvent.add(masterEvent);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return allMasterEvent;
    }

    public MasterEvent GetMasterEventById(MasterEvent masterEvent)
    {
        MasterEvent getMasterEvent = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "SELECT AGENT_EVENT, NAME, ADDRESS, NO_NPWP, INFORMATION FROM MASTER_EVENT WHERE MASTER_EVENT_ID = ?" ;

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterEvent.getMasterEventId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                getMasterEvent.setAgentEvent(resultSet.getString(1));
                getMasterEvent.setName(resultSet.getString(2));
                getMasterEvent.setAddress(resultSet.getString(3));
                getMasterEvent.setNoNpwp(resultSet.getString(4));
                getMasterEvent.setInformation(resultSet.getString(5));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return getMasterEvent;
    }

    public void DeleteMasterEventById (MasterEvent masterEvent)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(StaticPreference.URL, StaticPreference.USERNAME, StaticPreference.PASSWORD);

            String query = "DELETE FROM MASTER_EVENT WHERE MASTER_EVENT_ID = ?";

            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, masterEvent.getMasterEventId());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }

            if (preparedStatement != null)
            {
                preparedStatement.close();
            }

            if (statement != null)
            {
                statement.close();
            }

            if (connect != null)
            {
                connect.close();
            }
        }
        catch (Exception ignored)
        {}
    }
}
