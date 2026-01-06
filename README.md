# MaxPrestige

![Version](https://img.shields.io/badge/version-1.0-blue) ![Dependencies](https://img.shields.io/badge/dependencies-EZPrestige%20%7C%20Vault-red) ![License](https://img.shields.io/badge/license-MIT-green)

**MaxPrestige** is a "Quality of Life" extension for the [EZPrestige](https://www.spigotmc.org/resources/ezprestige.1794/) plugin. It allows players to ascend multiple prestige levels instantly based on their current balance.

Instead of typing `/prestige` dozens of times, this plugin calculates the maximum rank a player can afford and executes all necessary transitions in a single tick.

---

## 🚀 Features

* **Bulk Prestiging:** Automatically calculates how many levels a player can afford (e.g., jumping from P1 ➔ P50 instantly).
* **Safe Economy Handling:** Checks costs for every intermediate level to ensure the player has sufficient funds for the entire jump.
* **Deep Integration:** Uses reflection to hook directly into EZPrestige's internal storage, ensuring compatibility with your existing prestige configurations.
* **Command Execution:** Triggers all console commands associated with each skipped prestige level, so players don't miss out on rewards.
* **Custom Broadcast:** Announces the massive jump to the server (e.g., *"Player prestiged from P10-P25!"*).

---

## 📦 Installation & Building

### Prerequisites
* **Java 8+**
* **Spigot/Paper Server** (1.15+)
* **Dependencies:**
    * [EZPrestige](https://www.spigotmc.org/resources/ezprestige.1794/) (Required)
    * [Vault](https://www.spigotmc.org/resources/vault.34315/) (Required)

### Build Instructions
1.  Clone the repository:
2.  Build with Maven:
    *Note: You may need to install the EZPrestige jar to your local maven repository if it is not available in a public repo.*
3.  Place the generated `.jar` in your `/plugins/` folder.

---

## 🛠 Commands & Permissions

| Command | Permission | Description |
| :--- | :--- | :--- |
| `/maxprestige` | `maxprestige.command` | Calculates and executes the maximum amount of prestiges affordable. |

---

## ⚙️ Configuration

The configuration is simple and handles the global broadcast message.

`config.yml`:
```yaml
# Placeholders:
# %player% - The player's name
# %from%   - The starting prestige level
# %to%     - The final prestige level achieved
PrestigeMessage: "&6%player% prestiged from P%from%-P%to%!"
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
