package com.hellogwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.hellogwt.client.service.GreetingService;
import com.hellogwt.client.service.GreetingServiceAsync;
import com.hellogwt.shared.domain.Greeting;

import java.util.List;

public class HelloGWT implements EntryPoint {
    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private HorizontalPanel authorPanel = new HorizontalPanel();
    private HorizontalPanel textPanel = new HorizontalPanel();
    private HorizontalPanel editPanel = new HorizontalPanel();

    private Label authorLabel = new Label("Author:");
    private Label textLabel = new Label("Text:");
    private TextBox authorTextBox = new TextBox();
    private TextBox textTextBox = new TextBox();

    private Button addButton = new Button("Add");
    private Button updateButton = new Button("Update");
    private Button deleteButton = new Button("Delete");

    private FlexTable greetingsFlexTable = new FlexTable();

    @Override
    public void onModuleLoad() {
        initWidgets();
        initHandlers();

        refreshGreetingsTable();
    }

    private void initWidgets() {
        authorLabel.setWidth("50");
        authorTextBox.setWidth("100");
        authorPanel.add(authorLabel);
        authorPanel.add(authorTextBox);

        textLabel.setWidth("50");
        textTextBox.setWidth("100");
        textPanel.add(textLabel);
        textPanel.add(textTextBox);

        addButton.setWidth("50");
        updateButton.setWidth("50");
        deleteButton.setWidth("50");
        editPanel.add(addButton);
        editPanel.add(updateButton);
        editPanel.add(deleteButton);

        RootPanel.get().add(authorPanel);
        RootPanel.get().add(textPanel);
        RootPanel.get().add(editPanel);
        RootPanel.get().add(greetingsFlexTable);
    }

    private void initHandlers() {
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("ERROR: Cannot edit greetings!");
            }

            @Override
            public void onSuccess(Void result) {
                refreshGreetingsTable();
            }
        };

        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
                    greetingService.getGreeting(textTextBox.getText(), new AsyncCallback<Greeting>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("ERROR: Cannot find greeting!");
                        }

                        @Override
                        public void onSuccess(Greeting result) {
                            if (result == null) {
                                greetingService.addGreeting(authorTextBox.getText(), textTextBox.getText(), callback);
                            } else {
                                Window.alert("Greeting already exists!");
                            }
                        }
                    });
                } else {
                    Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
                }
            }
        });

        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
                    greetingService.updateGreeting(authorTextBox.getText(), textTextBox.getText(), callback);
                } else {
                    Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
                }
            }
        });

        deleteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                greetingService.deleteGreeting(textTextBox.getText(), callback);
            }
        });
    }

    private void refreshGreetingsTable() {
        greetingService.getGreetings(new AsyncCallback<List<Greeting>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("ERROR: Cannot load greetings!");
            }

            @Override
            public void onSuccess(List<Greeting> greetings) {
                fillGreetingsTable(greetings);
            }
        });
    }

    private void fillGreetingsTable(List<Greeting> greetings) {
        greetingsFlexTable.removeAllRows();

        greetingsFlexTable.setText(0, 0, "Author");
        greetingsFlexTable.setText(0, 1, "Text");

        for (Greeting greeting : greetings) {
            int row = greetingsFlexTable.getRowCount();

            greetingsFlexTable.setText(row, 0, greeting.getAuthor());
            greetingsFlexTable.setText(row, 1, greeting.getText());
        }
    }
}