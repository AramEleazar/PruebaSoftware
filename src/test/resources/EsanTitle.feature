Feature: Validación del Portal ESAN

  Scenario: Verificar el título principal de la página web de ESAN
    Given que el usuario navega al portal web de ESAN
    When la página web carga por completo
    Then el título de la pestaña debe ser "ESAN Graduate School of Business"

  Scenario: Cambiar el idioma del portal a inglés
    Given que el usuario navega al portal web de ESAN
    When el usuario hace clic en el botón de cambiar idioma
    Then la página debe cargar la versión en inglés del portal