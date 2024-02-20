import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cerca {
    public static Abbonato cercaAbbonato(String nome, String numeroTelefono) {
        String query = "SELECT * FROM Abbonato WHERE nome = ? AND numeroTelefono = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Abbonato abbonato = null;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, numeroTelefono);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                abbonato = new Abbonato(
                        resultSet.getString("nome"),
                        resultSet.getString("cognome"),
                        resultSet.getString("residenza"),
                        resultSet.getString("numeroTelefono"),
                        resultSet.getString("codicePromo"),
                        resultSet.getDouble("conto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return abbonato;
    }

    public static int contChiamate(String nome, String numeroTelefono) {
        String query = "SELECT contChiamate FROM Abbonato WHERE nome = ? AND numeroTelefono = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int chiamata = 0;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, numeroTelefono);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                chiamata = resultSet.getInt("contChiamate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return chiamata;
    }

    public static int contMessaggi(String nome, String numeroTelefono) {
        String query = "SELECT contMessaggi FROM Abbonato WHERE nome = ? AND numeroTelefono = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int messaggi = 0;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, numeroTelefono);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                messaggi = resultSet.getInt("contMessaggi");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi ResultSet e PreparedStatement
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messaggi;
    }

    public static int contDati(String nome, String numeroTelefono) {
        String query = "SELECT contDati FROM Abbonato WHERE nome = ? AND numeroTelefono = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int dati = 0;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, numeroTelefono);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dati = resultSet.getInt("contDati");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi ResultSet e PreparedStatement
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dati;
    }

    /*--------------------------------------------------------------------------------------------------------------------*/
    public static Promozione cercaPromo(String codice) {
        String query = "SELECT * FROM promozione WHERE codice = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Promozione promozione = null;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, codice);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                promozione = new Promozione(
                        resultSet.getString("nome"),
                        resultSet.getString("offerta"),
                        resultSet.getDouble("costo"),
                        resultSet.getString("codice")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return promozione;
    }
/*--------------------------------------------------------------------------------------------------------------------*/
    public static Promozione cercaPromoPerOfferta(String codice) {
        String query = "SELECT * FROM promozione WHERE offerta = ? LIMIT 0,1";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Promozione promozione = null;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, codice);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                promozione = new Promozione(
                        resultSet.getString("nome"),
                        resultSet.getString("offerta"),
                        resultSet.getDouble("costo"),
                        resultSet.getString("codice")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return promozione;
    }


    public static List<Promozione> getPromozioniDisponibili() {
        List<Promozione> promozioni = new ArrayList<>();
        String query = "SELECT * FROM Promozione";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Promozione promozione = new Promozione(
                        resultSet.getString("nome"),
                        resultSet.getString("offerta"),
                        resultSet.getDouble("costo"),
                        resultSet.getString("codice")
                );
                promozioni.add(promozione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return promozioni;
    }

    public static void aggiornaCodicePromoAbbonato(String numeroTelefono, String nuovoCodicePromo) {
        String query = "UPDATE Abbonato SET codicePromo = ? WHERE numeroTelefono = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = connessione.getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nuovoCodicePromo);
            preparedStatement.setString(2, numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi PreparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
