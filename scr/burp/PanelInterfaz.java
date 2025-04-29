package com.curiosidadesdehackers.burp;

import burp.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PanelInterfaz extends JPanel {
    private final IBurpExtenderCallbacks callbacks;
    private final IExtensionHelpers helpers;

    private JTextArea txtPeticion;
    private JTabbedPane pestañas;
    private JTextArea txtHtmlPoc;
    private JTextArea txtCurlPoc;
    private JTextArea txtJsPoc;

    public PanelInterfaz(IBurpExtenderCallbacks callbacks, IExtensionHelpers helpers) {
        this.callbacks = callbacks;
        this.helpers = helpers;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel superior: Entrada de la petición
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Petición HTTP"));

        txtPeticion = new JTextArea(10, 60);
        txtPeticion.setLineWrap(true);
        JScrollPane scrollPeticion = new JScrollPane(txtPeticion);
        panelSuperior.add(scrollPeticion, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnGenerar = new JButton("Generar POC CSRF");
        btnGenerar.addActionListener(e -> generarPocs());

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> txtPeticion.setText(""));

        JButton btnEjemplo = new JButton("Cargar Ejemplo");
        btnEjemplo.addActionListener(e -> cargarEjemplo());

        panelBotones.add(btnGenerar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnEjemplo);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // Panel inferior: Pestañas con los POCs generados
        pestañas = new JTabbedPane();

        // Pestaña HTML
        txtHtmlPoc = crearAreaTextoConCopiar();
        JScrollPane scrollHtml = new JScrollPane(txtHtmlPoc);
        pestañas.addTab("HTML POC", scrollHtml);

        // Pestaña cURL
        txtCurlPoc = crearAreaTextoConCopiar();
        JScrollPane scrollCurl = new JScrollPane(txtCurlPoc);
        pestañas.addTab("cURL POC", scrollCurl);

        // Pestaña JavaScript
        txtJsPoc = crearAreaTextoConCopiar();
        JScrollPane scrollJs = new JScrollPane(txtJsPoc);
        pestañas.addTab("JavaScript POC", scrollJs);

        // Crear JSplitPane para dividir los paneles
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelSuperior, pestañas);
        splitPane.setResizeWeight(0.5); // Divide el espacio equitativamente al inicio
        splitPane.setOneTouchExpandable(true); // Permite colapsar/expandir con un click
        splitPane.setContinuousLayout(true); // Actualización continua al mover el divisor

        // Añadir componentes al panel principal
        add(splitPane, BorderLayout.CENTER);

        // Panel inferior con créditos
        JLabel lblCreditos = new JLabel("Generado por CSRF POC Avanzado de Curiosidades De Hackers");
        lblCreditos.setHorizontalAlignment(JLabel.CENTER);
        lblCreditos.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(lblCreditos, BorderLayout.SOUTH);
    }

    private JTextArea crearAreaTextoConCopiar() {
        JTextArea areaTexto = new JTextArea(15, 60);
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);

        return areaTexto;
    }

    public void mostrarPeticion(String metodo, String url, String cuerpo) {
        StringBuilder peticion = new StringBuilder();
        peticion.append(metodo).append(" ").append(url).append(" HTTP/1.1\n");
        peticion.append("Host: ").append(extraerHost(url)).append("\n");
        peticion.append("\n").append(cuerpo);

        txtPeticion.setText(peticion.toString());
        generarPocs();
    }

    private String extraerHost(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception e) {
            return "";
        }
    }

    private void cargarEjemplo() {
        String ejemplo = "POST /index.com HTTP/1.1\n" +
                         "Host: ejemplo.com\n" +
                         "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0\n" +
                         "Content-Type: application/x-www-form-urlencoded\n" +
                         "Content-Length: 138\n" +
                         "\n" +
                         "subname=&subemail=test%40gmail.com&subscribe.x=ssssssss&subscribe.y=3&mlist=ensnc%26%2395804%3B%3ACanoga+%3Csvg+onload%3Dconfirm%281%29%3E";

        txtPeticion.setText(ejemplo);
    }

    private void generarPocs() {
        String peticion = txtPeticion.getText().trim();
        if (peticion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa una petición HTTP", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Analizar la petición
            AnalizadorPeticion analizador = new AnalizadorPeticion(peticion);

            // Generar los POCs
            GeneradorHtmlPoc generadorHtml = new GeneradorHtmlPoc();
            GeneradorCurlPoc generadorCurl = new GeneradorCurlPoc();
            GeneradorJsPoc generadorJs = new GeneradorJsPoc();

            txtHtmlPoc.setText(generadorHtml.generar(analizador));
            txtCurlPoc.setText(generadorCurl.generar(analizador));
            txtJsPoc.setText(generadorJs.generar(analizador));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al analizar la petición: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            callbacks.printError("Error generando POC CSRF: " + e.getMessage());
        }
    }
}
