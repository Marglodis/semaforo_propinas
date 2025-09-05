package dev.javfuentes.semaforopropinas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaración de las vistas
    private TextView tvTitulo;
    private EditText etMonto;
    private EditText etPorcentaje;
    private Button btnCalcularPropina;
    private TextView tvResultado;
    private ImageView ivEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar la vista desde el archivo XML
        setContentView(R.layout.activity_main);

        // Inicialización de las vistas
        tvTitulo = findViewById(R.id.tv_titulo);
        etMonto = findViewById(R.id.et_monto);
        etPorcentaje = findViewById(R.id.et_porcentaje);
        btnCalcularPropina = findViewById(R.id.btn_calcular_propina);
        tvResultado = findViewById(R.id.tv_resultado);
        ivEstado = findViewById(R.id.iv_estado);

        // Configurar el listener del botón
        btnCalcularPropina.setOnClickListener(v -> calcularPropina());
    }

    private void calcularPropina() {

        // Obtener texto de los campos
        String montoTexto = etMonto.getText().toString();
        String porcentajeTexto = etPorcentaje.getText().toString();

        // Validar que no estén vacíos
        if (montoTexto.isEmpty() || porcentajeTexto.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir a números enteros
        int monto = Integer.parseInt(montoTexto);
        int porcentaje = Integer.parseInt(porcentajeTexto);

        // Calcular propina y total
        int propina = monto * porcentaje / 100;
        int total = monto + propina;

        // Mostrar resultado
        String resultado = "Propina: $" + propina + "\nTotal: $" + total;
        tvResultado.setText(resultado);

        // Cambiar imagen según porcentaje
        cambiarImagen(porcentaje);
    }

    private void cambiarImagen(int porcentaje) {

        // Hacer visible la imagen cuando se calcula
        ivEstado.setVisibility(View.VISIBLE);

        if (porcentaje < 10) {
            // Propina baja - cara enojada
            ivEstado.setImageResource(R.drawable.enojado);
        } else if (porcentaje <= 20) {
            // Propina normal - cara neutra
            ivEstado.setImageResource(R.drawable.neutro);
        } else {
            // Propina generosa - cara feliz
            ivEstado.setImageResource(R.drawable.feliz);
        }
    }
}