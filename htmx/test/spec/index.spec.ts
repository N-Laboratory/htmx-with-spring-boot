import { afterAll, beforeAll, describe, expect, test } from 'vitest'
import { launch, PuppeteerLaunchOptions } from 'puppeteer'
import type { Browser, Page } from 'puppeteer'

// Launch option
// https://pptr.dev/api/puppeteer.browserlaunchargumentoptions
const options: PuppeteerLaunchOptions = {
  headless: false,
  slowMo: 50,
  defaultViewport: {
    width: 1280,
    height: 1024,
  },
  devtools: true,
  args: ['--window-size=1680,1024'],
}

type User = {
  email: string
  password: string
}

const newUser: User = {
  email: 'test@test.com',
  password: 'Test2024',
}

const editedUser: User = {
  email: 'test1234@test.com',
  password: 'Test@2024',
}

describe('index.html', () => {
  let browser: Browser
  let page: Page

  beforeAll(async () => {
    browser = await launch(options)
    page = await browser.newPage()
  })

  afterAll(async () => {
    await browser.close()
  })

  test('1: create user', async () => {
    // Go to top page
    await page.goto('http://localhost:3000/src/', {
      waitUntil: 'networkidle2',
    })

    // Input user info in the create modal
    await page.waitForSelector('[data-testid="create-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test1-1.png',
      fullPage: true,
    })
    await page.click('[data-testid="create-btn"]')
    await page.type('input[data-testid="input-email"]', newUser.email)
    await page.type('input[data-testid="input-password"]', newUser.password)
    await page.screenshot({
      path: 'test/screenshot/test1-2.png',
      fullPage: true,
    })
    await page.click('[data-testid="create-submit-btn"]')

    // Display the user information you have created
    await page.waitForSelector('[data-testid="result-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test1-3.png',
      fullPage: true,
    })
    const email = await page.$eval('[data-testid="email-text"]', (element) => element.textContent)
    const password = await page.$eval(
      '[data-testid="password-text"]',
      (element) => element.textContent,
    )
    await page.click('[data-testid="result-btn"]')

    // Created user shows in the user list
    await page.waitForSelector('[data-testid="td-email"]')
    await page.screenshot({
      path: 'test/screenshot/test1-4.png',
      fullPage: true,
    })
    const existsCreateUser = await page.$(`[data-testid='td-email']::-p-text(${newUser.email})`)

    // Assert
    expect(email).toBe(`Email: ${newUser.email}`)
    expect(password).toBe(`Password: ${newUser.password}`)
    expect(existsCreateUser).not.toBeNull()
  }, 20000)

  test('2: edit user', async () => {
    // Go to top page
    await page.goto('http://localhost:3000/src/', {
      waitUntil: 'networkidle2',
    })

    // Edit user info in the edit modal
    await page.waitForSelector('[data-testid="edit-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test2-1.png',
      fullPage: true,
    })
    await page.click('[data-testid="edit-btn"]')
    await page.$eval('input[data-testid="edit-email"]', (element) => (element.value = ''))
    await page.$eval('input[data-testid="edit-password"]', (element) => (element.value = ''))
    await page.type('input[data-testid="edit-email"]', editedUser.email)
    await page.type('input[data-testid="edit-password"]', editedUser.password)
    await page.screenshot({
      path: 'test/screenshot/test2-2.png',
      fullPage: true,
    })
    await page.click('[data-testid="edit-submit-btn"]')

    // Display the user information you have edited
    await page.waitForSelector('[data-testid="result-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test2-3.png',
      fullPage: true,
    })
    const email = await page.$eval('[data-testid="email-text"]', (element) => element.textContent)
    const password = await page.$eval(
      '[data-testid="password-text"]',
      (element) => element.textContent,
    )
    await page.click('[data-testid="result-btn"]')

    // Edited user shows in the user list
    await page.waitForSelector('[data-testid="td-email"]')
    await page.screenshot({
      path: 'test/screenshot/test2-4.png',
      fullPage: true,
    })
    const existsEditedUser = await page.$(`[data-testid='td-email']::-p-text(${editedUser.email})`)

    // Assert
    expect(email).toBe(`Email: ${editedUser.email}`)
    expect(password).toBe(`Password: ${editedUser.password}`)
    expect(existsEditedUser).not.toBeNull()
  }, 20000)

  test('3: delete user', async () => {
    // Go to top page
    await page.goto('http://localhost:3000/src/', {
      waitUntil: 'networkidle2',
    })

    // Delete user in the delete modal
    await page.waitForSelector('[data-testid="delete-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test3-1.png',
      fullPage: true,
    })
    await page.click('[data-testid="delete-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test3-2.png',
      fullPage: true,
    })
    await page.click('[data-testid="delete-submit-btn"]')

    // Display the deleted user information
    await page.waitForSelector('[data-testid="result-btn"]')
    await page.screenshot({
      path: 'test/screenshot/test3-3.png',
      fullPage: true,
    })
    const email = await page.$eval('[data-testid="email-text"]', (element) => element.textContent)
    const password = await page.$eval(
      '[data-testid="password-text"]',
      (element) => element.textContent,
    )
    await page.click('[data-testid="result-btn"]')

    // Deleted user does not show in the user list
    await page.waitForSelector('[data-testid="empty-user-list"]')
    await page.screenshot({
      path: 'test/screenshot/test3-4.png',
      fullPage: true,
    })
    const existsDeletedUser = await page.$(`[data-testid='td-email']::-p-text(${editedUser.email})`)

    // Assert
    expect(email).toBe(`Email: ${editedUser.email}`)
    expect(password).toBe(`Password: ${editedUser.password}`)
    expect(existsDeletedUser).toBeNull()
  }, 20000)
})
