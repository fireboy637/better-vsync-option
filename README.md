# Better Vsync Option

Just a simple mod that replaces "Vsync" button with new "Present mode" button, which makes you choose your custom present mode instead of auto-selected ones.

It **only seems useful on Vulkan** because OpenGL only has `FIFO` and `IMMEDIATE`.

All Mojang currently supported present modes:

| Present Mode   | Description                                                                                                                   | Screen Tearing                                             | Input latency                                        |
|----------------|-------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------|------------------------------------------------------|
| `FIFO`         | Standard vsync. Presents frames at a fixed interval matching the display refresh rate.                                        | ❎ (No tearing)                                             | High - especially when FPS drops below refresh rate. |
| `FIFO_RELAXED` | "Improved" vsync. Presents next frame immediately when a frame takes too long, instead of waiting for the next refresh cycle. | ⚠️（Slight tearing possible when FPS is below refresh rate) | Mid - generally better than `FIFO`.                  |
| `MAILBOX`      | Always presents the latest fully rendered frame, discarding older queued frames.                                              | ❎ (No tearing)                                             | Low - close to `IMMEDIATE`.                          |
| `IMMEDIATE`    | Presents the latest frame as soon as it is rendered, even if the previous frame is still being displayed.                     | ❗(Always causes tearing)                                   | Lowest - as its name implies.                        |

**In most cases, `FIFO_RELAXED` (for smoother experience on vsync, with slight screen tearing) and `MAILBOX` (for no screen tearing without vsync) offer the best balance between visual quality and responsiveness.**

_Also check [Vulkan Document](https://docs.vulkan.org/refpages/latest/refpages/source/VkPresentModeKHR.html)!_
