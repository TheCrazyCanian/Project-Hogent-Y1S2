package persistentie;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {
	
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord) {
    	
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
                
        	PreparedStatement query = conn.prepareStatement("SELECT * FROM ID343634_g12.Speler WHERE gebruikersnaam = ? AND wachtwoord = ?");
            query.setString(1, gebruikersnaam);
            query.setString(2, wachtwoord);
             
            try ( ResultSet rs = query.executeQuery()) {
            	if (rs.next()) {
                    speler = new Speler(rs.getString("Gebruikersnaam"), rs.getString("wachtwoord"));
                }
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return speler;
    }
    
    public int geefScoreVanSpeler(String gebruikersnaam) {
    	int score = -1;
    	
    	 try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
             
         	PreparedStatement query = conn.prepareStatement("SELECT * FROM ID343634_g12.Speler WHERE gebruikersnaam = ?");
             query.setString(1, gebruikersnaam);
              
             try ( ResultSet rs = query.executeQuery()) {
             	if (rs.next()) {
                     score = rs.getInt("score");
                 }
             }
         } 
         catch (SQLException e) {
             throw new RuntimeException(e);
         }
    	 
    	 return score;
    }
    
    public void updateScoreVanSpeler(String gebruikersnaam, int toeTeVoegenScore) {
    	int totaalScore = geefScoreVanSpeler(gebruikersnaam) + toeTeVoegenScore;
    	
    	try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("UPDATE ID343634_g12.Speler SET score = ? WHERE gebruikersnaam = ?")) {
            query.setInt(1, totaalScore);
            query.setString(2, gebruikersnaam);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

	public List<String> geefNamenEnScoresDatabank(List<String> gebruikersnamen) {
		List<String> effectieveNamenEnScores = new ArrayList<>();
		List<String> alleNamenEnScores = geefSpelersMetScore();
		for(String s : alleNamenEnScores) {
			int i = 0;
			String[] strs = s.split(";");
			while (i < gebruikersnamen.size()) {
				if(strs[0].equals(gebruikersnamen.get(i))) {
					effectieveNamenEnScores.add(strs[0]);
					effectieveNamenEnScores.add(strs[1]);
				}
				i++;
			}
		}
		return effectieveNamenEnScores;
	}
	
	private List<String> geefSpelersMetScore() {
    	List<String> gebruikersMetScore = new ArrayList<>();
    	
   	 try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            
        	PreparedStatement query = conn.prepareStatement("SELECT gebruikersnaam, score FROM ID343634_g12.Speler ORDER BY score DESC, gebruikersnaam");
                         
            try ( ResultSet rs = query.executeQuery()) {
            	while(rs.next()) {
            		String gebruikersnaam = rs.getString("gebruikersnaam");
            		int score = rs.getInt("score");
            		gebruikersMetScore.add(String.format("%s;%d", gebruikersnaam, score));
            	}
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
   	 return gebruikersMetScore;   	 
    }
}
