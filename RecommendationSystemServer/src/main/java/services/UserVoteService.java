/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import Repositories.UserVoteRepository;
import customexception.UnableToConnectDatabase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;
import models.UserVote;
import services.Interfaces.IUserVoteService;

/**
 *
 * @author ria.mishra
 */
public class UserVoteService implements IUserVoteService{
    
    private final UserVoteRepository repository;

    public UserVoteService() throws UnableToConnectDatabase {
        this.repository = new UserVoteRepository();
    }

    @Override
    public void addUserVote(ObjectInputStream input) throws IOException {
        try {
            List<UserVote> userVotes = (List<UserVote>) input.readObject();
            this.repository.addVote(userVotes);
        } catch (SQLException ex) {
            throw new IOException("Failed to add user vote");
        } catch (Exception ex) {
            throw new IOException ("Unexpected Server issue");
        }
        
    }
    
}
