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

        try {
            // Convertir a números
            double monto = Double.parseDouble(montoTexto);
            int porcentaje = Integer.parseInt(porcentajeTexto);

            // Calcular propina y total
            double propina = monto * porcentaje / 100.0;
            double total = monto + propina;

            // Mostrar resultado con formato mejorado
            String resultado = String.format("💰 Propina: $%.0f\n💳 Total: $%.0f", propina, total);
            tvResultado.setText(resultado);
            tvResultado.setVisibility(View.VISIBLE); // Hacer visible el resultado

            // Cambiar imagen según porcentaje
            cambiarImagen(porcentaje);

            Toast.makeText(this, "¡Cálculo realizado!", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingresa números válidos", Toast.LENGTH_SHORT).show();
        }
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