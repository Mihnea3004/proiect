/// <reference types="Cypress" />
import 'cypress-iframe'

describe('Youtube Player Commands', () => {
  it('Play/Pause', () => {
    cy.on('uncaught:exception', (err,runnable) =>{
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(5000);
    cy.get('.ytp-play-button').click({force:true});
    cy.get('#player').should('be.visible')
    cy.get('video').its('0.paused').should('equal', true);
    cy.wait(5000);
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > button').click({force:true});
    cy.get('#player').should('be.visible');
    cy.get('video').its('0.paused').should('equal', false);

  })
  it('Next', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > a.ytp-next-button.ytp-button').click();
    cy.wait(500)
    cy.location().should((location) => {
      expect(location.href).to.not.eq('https://www.youtube.com/watch?v=bziEoDoH7ks');
  })
})
  it('Volume Change', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.document().then((doc) => {
      const initialVolume = doc.querySelector("#movie_player").volume;
      doc.querySelector("#movie_player").volume = 50;
      const currentVolume = doc.querySelector("#movie_player").volume;
      expect(currentVolume).to.not.equal(initialVolume);
    })
      
    });
  it('Autoplay On', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('video').then(($video) => {
      $video[0].playbackRate = 4
      $video[0].play()
    })
    cy.get('video', { timeout: 200000 }).and('have.prop', 'ended', true).then(($video) => {
      cy.location().should((location) => {
        expect(location.href).to.not.eq('https://www.youtube.com/watch?v=bziEoDoH7ks');
    })
  })
  })
  it('Autoplay Off', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button:nth-child(2)').click({force:true});
    cy.get('video').then(($video) => {
      $video[0].playbackRate = 4
      $video[0].play()
    })
    cy.get('video', { timeout: 200000 }).and('have.prop', 'ended', true).then(($video) => {
      cy.location().should((location) => {
        expect(location.href).to.eq('https://www.youtube.com/watch?v=bziEoDoH7ks');
    })
  })
  })
  it('Subtitles On/Off', () => {
    cy.on('uncaught:exception', (err,runnable) => {
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-subtitles-button.ytp-button').click({force:true}).should('have.attr','aria-pressed', 'true');
    cy.xpath('//*[@id="caption-window-1"]/span').should('be.visible');
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-subtitles-button.ytp-button').click({force:true}).should('have.attr','aria-pressed', 'false');
    cy.xpath('//*[@id="caption-window-1"]/span').should('not.exist')
  })
  it('Miniplayer On/Off', () => {
    cy.on('uncaught:exception', (err,runnable) =>{
      return false;
    })
    cy.visit("https://www.youtube.com/watch?v=bziEoDoH7ks");
    cy.xpath('//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]/yt-button-shape/button').click();
    cy.wait(500);
    cy.get('#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-right-controls > button.ytp-miniplayer-button.ytp-button',{timeout:10000}).click({force:true});
    cy.wait(500);
    cy.location().should((location) => {
      expect(location.href).to.not.eq('https://www.youtube.com/watch?v=bziEoDoH7ks');
  })
    cy.get('#movie_player > div.ytp-miniplayer-ui > div > button.ytp-miniplayer-expand-watch-page-button.ytp-button.ytp-miniplayer-button-top-left',{timeout:10000}).click({force:true});
    cy.wait(500);
    cy.location().should((location) => {
      expect(location.href).to.eq('https://www.youtube.com/watch?v=bziEoDoH7ks');
  })
  })
});