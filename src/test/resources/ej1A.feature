Feature: Project

  Scenario: Creacion y modificacion de usuario


    Given Crear un nuevo usario
    When envio POST request a /api/user.json
    """
    {
      "FullName":"Ignacio Valencia"
      "Email":"usuarioGenerico@api.com",
      "Password":"usuarioGenerico123",
    }
    """
    Then la respuesta seria 200
    And expected body
    """
    {
        "FullName": "Ignacio Valencia",
        "Email": "usuarioGenerico@api.com",
        "Password":"usuarioGenerico123",
        "Id": "IGNORE",
        "TimeZone":"IGNORE",
        "IsProUser": false,
        "DefaultProjectId": "IGNORE",
        "AddItemMoreExpanded": false,
        "EditDueDateMoreExpanded":  "IGNORE",
        "ListSortType": 0,
        "FirstDayOfWeek": "IGNORE",
        "NewTaskDueDate": "IGNORE"
    }
    """
    
    And se guarda Id en ID_USR
    
    Given authenticacion
    
    When envio PUT request a /api/user/0.json
    """
    {
      "Email":"usuarioGenerico@api.com"
    }
    """
    Then la respuesta seria 200
    
    And el atributo Email deberia ser usuarioGenerico@api.com
    
    
