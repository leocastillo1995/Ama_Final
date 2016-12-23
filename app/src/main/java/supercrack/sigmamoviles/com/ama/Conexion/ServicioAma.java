package supercrack.sigmamoviles.com.ama.Conexion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.Modelo.Usuario;

/**
 * Created by eglp on 22/12/2016.
 */

public interface ServicioAma {

    public static final String URL = "https://api-ama.herokuapp.com";

    @FormUrlEncoded
    @POST("/o/token/")
    Call<Token> Idendificador(@Field("client_id") String client_id ,
                              @Field("client_secret") String client_secret ,
                              @Field("username") String username ,
                              @Field("password") String password ,
                              @Field("grant_type") String grant_type);


    @GET("api/usuario/all/")
    Call<ArrayList<Usuario>> getlista(@Header("Authorization") String token);

    @POST("api/usuario/registrar/")
    Call<Usuario> registrarusuario(@Body Usuario usuario);

}
