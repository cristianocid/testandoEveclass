Feature: Contato Eveclass

  Scenario:Mandar mensagem de contato para Eveclass
    Given que acesso o site Testando_Eveclass
    When clico no botao Começar_Agora
    When clico no menu Contato
    When insiro o Nome
    When insiro a Email
    When insiro a Mensagem
    When clico no botao Enviar
    Then exibe o pop up de confirmaçao e clico no botao Fechar