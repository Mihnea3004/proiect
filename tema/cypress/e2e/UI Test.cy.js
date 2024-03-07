describe('UI Test', () => {
  it('Youtube Logo Visibility/Functionality', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#logo-icon').should('be.visible');
    cy.get('#logo-icon').click({force:true});
    cy.location().should((location) => {
      expect(location.href).to.eq('https://www.youtube.com/');
    })
  })
  it('Youtube Search Bar Visibility/Functionality + Clear Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('input[id="search"]').type('adelante',{force:true}).should('be.visible');
    cy.get('#search-clear-button > ytd-button-renderer > yt-button-shape > button').should('be.visible').click();
    cy.get('input[id="search"').should('have.value','');
    cy.get('input[id="search"]').type('adelante',{force:true}).should('be.visible');
    cy.get('#search-icon-legacy').click({force:true});
    cy.wait(500);
    cy.location().should((location) => {
      expect(location.href).to.eq('https://www.youtube.com/results?search_query=adelante');
    })
    cy.contains('adelante',{matchCase:false});
    cy.get('#contents > ytd-video-renderer:nth-child(1)').should('be.visible');
  })
  it('Youtube Search Bar Suggestions', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.on('test:before:run', (err,runnable) =>{
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('input[id="search"]').type('adelant',{force:true}).should('be.visible').click();
    cy.wait(500);
    cy.get('#search-icon').should('be.visible');
    cy.wait(500);
    cy.get('body > div > div:nth-child(2) > div.sbdd_b').should('be.visible');
    cy.get('#sbse0').click();
    cy.wait(500);
    cy.location().should((location) => {
      expect(location.href).to.eq('https://www.youtube.com/results?search_query=adelante');
    })
    cy.contains('adelante',{matchCase:false});
    cy.get('#contents > ytd-video-renderer:nth-child(1)').should('be.visible');
  })  
})
  it('Youtube Left Drawer Hide/Show', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#scrim').should('have.class',"style-scope tp-yt-app-drawer");
    cy.get('#guide-button').should('be.visible').click();
    cy.wait(500);
    cy.get('#scrim').should('have.class',"style-scope tp-yt-app-drawer visible");
    cy.get('#guide-content').should('be.visible').click();
    cy.wait(500);
    cy.get('#scrim').should('have.class',"style-scope tp-yt-app-drawer");
  })
  it('Youtube Shorts Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#items > ytd-mini-guide-entry-renderer:nth-child(2)').should('be.visible').click();
    cy.location().should((location) => {
      expect(location.href).to.contain('https://www.youtube.com/shorts');
    })
  })
  it('Youtube Home Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#items > ytd-mini-guide-entry-renderer:nth-child(1)').should('be.visible').click();
    cy.location().should((location) => {
      expect(location.href).to.contain('https://www.youtube.com');
    })
  })
  it('Youtube Subscriptions Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#items > ytd-mini-guide-entry-renderer:nth-child(3)').should('be.visible').click();
    cy.location().should((location) => {
      expect(location.href).to.contain('https://www.youtube.com/feed/subscriptions');
    })
  })
  it('Youtube You Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#items > ytd-mini-guide-entry-renderer:nth-child(4)').should('be.visible').click();
    cy.location().should((location) => {
      expect(location.href).to.contain('https://www.youtube.com/feed/you');
    })
  })
  it('Youtube History Button', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit('https://www.youtube.com/');
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#items > ytd-mini-guide-entry-renderer:nth-child(5)').should('be.visible').click();
    cy.location().should((location) => {
      expect(location.href).to.contain('https://www.youtube.com/feed/history');
    })
  })
})