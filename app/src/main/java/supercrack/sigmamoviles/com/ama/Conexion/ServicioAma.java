package supercrack.sigmamoviles.com.ama.Conexion;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.ComentarioForo;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.PreguntaOpciones;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.RegistroComentario;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Tema;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Token;
import supercrack.sigmamoviles.com.ama.Modelo.Conexion.Usuario;

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

    @GET("api/usuario/obtener/")
    Call<Usuario> getobtener(@Header("Authorization") String token);

    @PUT("api/usuario/actualizar/")
    Call<Usuario> getactualizar(@Header("Authorization") String token , @Body Usuario usuario);

    @GET("api/foro/temas/1072a57a-6b50-47f6-865e-28fea29e1de9/")
    Call<ArrayList<Tema>> getlistaTema(@Header("Authorization") String token);

    @POST("api/foro/temas/comentario/agregar/")
    Call<ComentarioForo> registroComentario(@Header("Authorization") String token , @Body RegistroComentario registroComentario);

    @GET("api/foro/temas/{uuid}/comentarios/")
    Call<ArrayList<ComentarioForo>> getlistacomentario(@Path("uuid") String id , @Header("Authorization") String token);

    @GET("api/encuesta/preguntas/1072a57a-6b50-47f6-865e-28fea29e1de9/")
    Call<JsonObject> getlistaprregunta(@Header("Authorization") String token);

    @POST("api/encuesta/respuesta/registrar/")
    Call<PreguntaOpciones> registrarpregunta(@Header("Authorization") String token , @Body PreguntaOpciones preguntaOpcion);

    @GET("api/contenido/all/")
    Call<JsonArray> getlistaedades(@Header("Authorization") String token);
}
