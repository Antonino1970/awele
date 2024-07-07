package io.swagger.api;

import AweleModel.Connexion;
import AweleModel.Jeu;
import AweleModel.ScoreDataClass;
import io.swagger.model.RestBoard;
import io.swagger.model.RestPlayer;
import io.swagger.model.RestScore;
import io.swagger.model.RestTabScore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-06T18:38:09.606973459Z[GMT]")
@RestController
public class AweleApiController implements AweleApi {

    private static final Logger log = LoggerFactory.getLogger(AweleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AweleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> connect(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name
) {
        String accept = request.getHeader("Accept");
        Jeu jeu = Jeu.getInstance();
        return new ResponseEntity<Integer>(jeu.connection(name), HttpStatus.OK);
    }

    public ResponseEntity<String> deconnect(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "playerId", required = false) Integer playerId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            String s = jeu.deconnecteJoueur(playerId);
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }
        return new ResponseEntity<String>("erreur",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RestBoard> getBoard() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            return new ResponseEntity<RestBoard>(jeu.setTabToRestBoard(), HttpStatus.OK);
        }
        return new ResponseEntity<RestBoard>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<RestPlayer> getPlayer(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "id", required = false) Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            return new ResponseEntity<RestPlayer>(jeu.getRestPlayer(id), HttpStatus.OK);
        }
        return new ResponseEntity<RestPlayer>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RestTabScore> getScore(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Connexion connexion = new Connexion("DatabaseScore.db");
            connexion.connect();
            RestTabScore tabScore = new RestTabScore();
            ResultSet resultSet;

            // Préparer la requête SQL
            if (name == null || name.isEmpty()) {
                resultSet = connexion.query("SELECT * FROM T_Score");
            }
            else {
                String nom = name;
                resultSet = null;
                try {
                    String query = "SELECT * FROM T_Score WHERE pseudoJ1 = LOWER(?) OR pseudoJ2 = LOWER(?)";
                    //String query = "SELECT * FROM T_Score WHERE pseudoJ1 = ? OR pseudoJ2 = ?";
                    PreparedStatement preparedStatement = connexion.getConnection().prepareStatement(query);
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, nom);
                    resultSet = preparedStatement.executeQuery();


                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            try {
                while (resultSet.next()) {
                    RestScore sc = new RestScore();
                    sc.setId(resultSet.getInt("idScore"));
                    sc.setPlayer1(resultSet.getString("pseudoJ1"));
                    sc.setPlayer2(resultSet.getString("pseudoJ2"));

                    Date sqlDate = resultSet.getDate("date");
                    // Définir le format désiré pour la conversion
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    // Convertir java.sql.Date en chaîne de caractères
                    String formattedDate = dateFormat.format(sqlDate);
                    sc.setDate(formattedDate);

                    sc.setScorePlayer1(resultSet.getInt("scoreJ1"));
                    sc.setScorePlayer2(resultSet.getInt("scoreJ2"));

                    tabScore.addScoreItem(sc);
                }
                connexion.close();
                return new ResponseEntity<>(tabScore, HttpStatus.OK);
            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Integer> getState() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            return new ResponseEntity<Integer>(jeu.getStatus(), HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RestPlayer> getTurn() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            return new ResponseEntity<RestPlayer>(jeu.getRestPlayer(jeu.getTurn()), HttpStatus.OK);
        }
        return new ResponseEntity<RestPlayer>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> sow(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "hole", required = false) Integer hole
,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "id", required = false) Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            //verification du tour du joueur
            if (jeu.getPlayerById(id)!= null &&  jeu.getPlayerById(id).getId() != jeu.getTurn() ) {
                System.out.println("ce n'est pas votre tour");
                return new ResponseEntity<Integer>(-4, HttpStatus.OK);
            }
            // verification du status du jeu
            else if (jeu.getStatus()==-1){
                System.out.println("Appuyer sur Start");
                return new ResponseEntity<Integer>(-5,HttpStatus.OK);
            } else if (hole<1||hole>6) {
                System.out.println("uniquement de 1 à 6 ");
                return new ResponseEntity<Integer>(-6,HttpStatus.OK);
            }
            // semaille
            else
                return new ResponseEntity<Integer>(jeu.gameSow(hole,id),HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> startstop(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "id", required = false) Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Jeu jeu = Jeu.getInstance();
            return new ResponseEntity<String>(jeu.jeuStartStop(id), HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

}
