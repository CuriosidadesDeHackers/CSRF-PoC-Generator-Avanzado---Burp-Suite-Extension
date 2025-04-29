package com.curiosidadesdehackers.burp;

import burp.*;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GeneradorCsrfPoc implements IBurpExtender, IContextMenuFactory, ITab {
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    private PrintWriter stderr;
    private PanelInterfaz panelInterfaz;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        this.stdout = new PrintWriter(callbacks.getStdout(), true);
        this.stderr = new PrintWriter(callbacks.getStderr(), true);

        // Configurar la extensión
        callbacks.setExtensionName("Generador CSRF POC Avanzado por Curiosidades De Hackers");
        callbacks.registerContextMenuFactory(this);

        // Crear la interfaz de usuario
        panelInterfaz = new PanelInterfaz(callbacks, helpers);
        callbacks.addSuiteTab(this);

        // Mostrar mensaje de inicio
        stdout.println("==============================================");
        stdout.println(" Generador CSRF POC Avanzado cargado correctamente");
        stdout.println(" Creado por Curiosidades De Hackers");
        stdout.println("==============================================");
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        List<JMenuItem> menuItems = new ArrayList<>();

        // Solo mostrar el menú para mensajes de solicitud
        if (invocation.getInvocationContext() == IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST ||
            invocation.getInvocationContext() == IContextMenuInvocation.CONTEXT_MESSAGE_VIEWER_REQUEST) {

            JMenuItem generarPocItem = new JMenuItem("Generar POC CSRF");
            generarPocItem.addActionListener(e -> {
                IHttpRequestResponse[] mensajes = invocation.getSelectedMessages();
                if (mensajes != null && mensajes.length > 0) {
                    byte[] solicitud = mensajes[0].getRequest();
                    IRequestInfo analisis = helpers.analyzeRequest(solicitud);

                    // Extraer información de la petición
                    String metodo = analisis.getMethod();
                    String url = analisis.getUrl().toString();
                    String cuerpo = new String(solicitud).substring(analisis.getBodyOffset());

                    // Mostrar en el panel de la interfaz
                    panelInterfaz.mostrarPeticion(metodo, url, cuerpo);
                }
            });
            menuItems.add(generarPocItem);
        }

        return menuItems;
    }

    @Override
    public String getTabCaption() {
        return "CSRF POC";
    }

    @Override
    public Component getUiComponent() {
        return panelInterfaz;
    }
}
