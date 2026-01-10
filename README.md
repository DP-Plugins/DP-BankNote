<center><img src="https://i.postimg.cc/MKPVVR1s/dplogo-512.png" alt="logo"></center>
<center><img src="https://i.postimg.cc/RZ9dqPFx/introduce.png" alt="introduce"></center>

Example Video : *Coming soon!*

This plugin allows players on a Minecraft server to turn their in-game money into physical **banknote items** that they can carry, trade, or redeem later.  
Enhance your server’s economy by letting players safely withdraw and share currency as tangible notes, making trading and money handling more interactive – all without complicated setup or configuration!

---

<center><img src="https://i.postimg.cc/RZ9dqP08/description.png" alt="description"></center>

- Convert in-game currency into **physical banknote items** that players can hold, trade, or store as money  
- Players can **redeem** a banknote by simply right-clicking it to deposit its value back into their balance  
- Easily customize the banknote’s appearance (item type, name, lore) through an intuitive **in-game GUI**, with dynamic placeholders for amount and player name  
- Flexible settings to define a maximum **amount limit** per banknote and optionally allow **decimal currency** values for precise economies  
- Seamless integration with Vault-compatible **economy plugins** via DPP-Core, ensuring all withdrawals and deposits accurately reflect in players’ balances  

---

<center><img src="https://i.postimg.cc/rwcjzhpH/depend-plugin.png" alt="depend-plugin"></center>

- All DP-Plugins require the **`DPP-Core`** plugin.  
- The plugin will not work if **`DPP-Core`** is not installed.  
- You can download **`DPP-Core`** here: <a href="https://github.com/DP-Plugins/DPP-Core/releases" target="_blank">Click me!</a>  

---

<center><img src="https://i.postimg.cc/dV01RxJB/installation.png" alt="installation"></center>

1️⃣ Place the **`DPP-Core`** plugin and this plugin file (**`DP-Banknote-*.jar`**) into your server’s **`plugins`** folder.  

2️⃣ Restart the server, and the plugin will be automatically enabled.  

3️⃣ If needed, you can open and modify **`config.yml`** and **`plugin.yml`** to customize settings.  

---

<center><img src="https://i.postimg.cc/jSKcC85K/settings.png" alt="settings"></center>

- **`config.yml`**: Manages basic plugin settings (such as prefix, language, off-hand redemption, decimal support, and amount limits).  

---

<center><img src="https://i.postimg.cc/SxqdjZKw/command.png" alt="command"></center>

❗ Some commands require admin permission (`dpbn.admin`).  

**Command List and Examples**

| Command | Permission | Description | Example |
|-------|------------|-------------|---------|
| `/dpbn item` | dpbn.admin | Open the banknote item editor GUI | `/dpbn item` |
| `/dpbn get <amount> (quantity)` | dpbn.use | Withdraw balance as banknote(s) | `/dpbn get 5000`<br>`/dpbn get 1000 5` |
| `/dpbn reload` | dpbn.admin | Reload configuration files | `/dpbn reload` |

**❗Notes when using commands**

- A **banknote item design** must be configured via `/dpbn item` before players can use banknotes  
- Withdrawal amounts must be positive and within the configured maximum limit  
- Players must have sufficient balance to create banknotes  
- Banknotes are redeemed by right-clicking (main hand by default; off-hand optional via config)  
- Admin commands require **OP** or the `dpbn.admin` permission  

---

<center><img src="https://i.postimg.cc/Z5ZH0fqL/api-integration.png" alt="api-integration"></center>

- You can use plugin's placeholders such as **`<amount> and <player>`**
- <amount> is balance, <player> is player's name
- use this placeholder for items name and lore

---

<center><a href="https://discord.gg/JnMCqkn2FX"><img src="https://i.postimg.cc/4xZPn8dC/discord.png" alt="discord"></a></center>

- https://discord.gg/JnMCqkn2FX  
- If you have any questions or issues, please contact your server administrator or refer to the plugin distribution page.  
- Feel free to leave suggestions for new features or improvements anytime!

---
